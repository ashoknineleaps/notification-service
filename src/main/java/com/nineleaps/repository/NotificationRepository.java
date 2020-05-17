package com.nineleaps.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.nineleaps.model.Notification;

@Repository
public interface NotificationRepository extends CassandraRepository<Notification, UUID>{

}
