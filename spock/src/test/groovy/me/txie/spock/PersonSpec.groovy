package me.txie.spock

import spock.lang.Specification

class PersonSpec extends Specification {
    def "智商低于70，就是傻"() {
        when: "智商69"
        Person me = new Person("Tian", "Xie", 69)

        then: "傻了吧唧"
        me.isFool()
    }
}
