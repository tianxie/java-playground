# Spring Boot 和 Kafka 的集成测试

https://www.baeldung.com/spring-boot-kafka-testing

在写集成测试时，不应该依赖外部服务。

## 使用 Embedded Kafka 测试 Kafka

See me.txie.springbootkafkatesting.EmbeddedKafkaIntegrationTest

依赖 spring-kafka-test。

如果一个测试修改了 context， @DirtiesContext 会给后续的测试提供一个全新的 context。

## 使用 TestContainers 测试 Kafka

See me.txie.springbootkafkatesting.KafkaTestContainersLiveTest



