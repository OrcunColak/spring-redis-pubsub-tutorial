# Read Me

The original idea is from

https://towardsdev.com/cqrs-in-action-building-robust-micro-services-with-spring-boot-68faa9133a4d

# Delivery Semantics

From  
https://medium.com/@aditimishra_541/redis-pub-sub-vs-apache-kafka-choosing-the-right-messaging-system-3e3130ac653c

- Only subscribers connected to the channel at the time of message delivery will receive the message.
- If a subscriber disconnects, they will miss any messages sent during that time.
- Redis Pub/Sub does not store messages. Once delivered, messages are deleted from the channel immediately.

- Redis provides at-most-once delivery for connected subscribers.
- If no subscribers are connected when a message is sent, the message is lost and will not be delivered later.
- As the number of subscribers grows, Redis Pub/Sub may experience performance degradation because it must deliver the
  message to all subscribers before deleting it.

# Redis Pub/Sub Limitations:

**No message persistence**: If subscribers disconnect, they miss any messages sent during that time.
**Scalability issues**: Performance decreases with a large number of subscribers due to push-based message delivery to
all connected clients.
**Single-threaded broker**: Redis is mostly single-threaded, limiting its concurrency unless you scale with additional
nodes.


