package com.finartz.ticketreservation.infrastructure.repository;

import com.finartz.ticketreservation.domain.PaymentInformation;
import com.finartz.ticketreservation.domain.PaymentInformationRepository;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DefaultMongoRepository implements PaymentInformationRepository {

    private final com.mongodb.client.MongoClient mongoClient;

    private final MongoCollection<PaymentInformation> mongoCollection;

    public DefaultMongoRepository(
            MongoConfiguration configuration,
            com.mongodb.client.MongoClient mongoClient) {
        this.mongoClient = mongoClient;

        final String mongoDbName = configuration.getMongoDbName();
        final String mongoCollectionName = configuration.getMongoCollectionName();


        final CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        final MongoDatabase mongoDatabase = this.mongoClient.getDatabase(mongoDbName).withCodecRegistry(pojoCodecRegistry);
        mongoCollection = mongoDatabase.getCollection(mongoCollectionName, PaymentInformation.class);
    }


    @Override
    public void save(PaymentInformation paymentInformation) {
        mongoCollection.insertOne(paymentInformation);
    }

    @Override
    public Optional<PaymentInformation> findByTicketId(String ticketId) {
        final Bson filter = Filters.eq("ticket.ticketId", ticketId);

        final List<PaymentInformation> paymentInformationList = mongoCollection.
                find(filter)
                .limit(1)
                .into(new ArrayList<>());

        return paymentInformationList.isEmpty() ? Optional.empty() : Optional.of(paymentInformationList.get(0));
    }

    @Override
    public void delete(PaymentInformation paymentInformation) {
        final Bson filter = Filters.eq("ticket.ticketId",paymentInformation.getTicket().getTicketId());

        mongoCollection.deleteOne(filter);
    }
}
