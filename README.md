# adverity-test

## How to run?
```$ gradle test```

## Task 1

Implemented in `ApiTest`

## Task 2

Implemented in `TrelloSpec`

### The problem

Automate testing of Trello API. Different roles should be able to create cards, update them and get notifications.

### Approach

1. Use Screen Play pattern to differentiate roles
2. Use Apache Fluent HTTP to run HTTP requests
3. Use maps as DTO