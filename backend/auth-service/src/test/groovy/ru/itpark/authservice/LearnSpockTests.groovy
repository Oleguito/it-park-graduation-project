package ru.itpark.authservice

import spock.lang.Specification


class LearnSpockTests extends Specification {

    Publisher publisher = new Publisher()
    Subscriber subscriber = Mock()
    Subscriber subscriber2 = Mock()

    def "Еще кое-чето из спока"() {
        when:
        publisher.send("hello")

        then: "Без блока interaction будет ошибка"
        interaction {
            def message = "hello"
            /* _ означает "что-то одно, но неважно что"
            * то есть любое число вызовов */
            _ * subscriber.receive(message)
        }
    }

    def "Что-то да проверяем.. не?"() {
        def a = 2
        def b = a * 3
        expect:
        a + b == 8
    }
}

class Publisher {
    def send(String message) {

    }
}

class Subscriber {
    def receive(String message) {

    }
}