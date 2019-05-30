# ChattingStudy
Chatting(Spring boot + kafka + react)

## Introduction

This architecture is:
```text
React ----- Spring boot ----- Kafka/zookeeper
                 |
                 |
               Cache(Redis로 변경)
```

- React frontend (messages are sent via websockets(STOMP))
- Kafka stores and distributes the chat messages
- Google common caches stores the state of the chat meesages and in the future, I will change to redis.
- Spring boot is http communication with React
