package classes

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MyDelegate(private val value: HashSet<Animal>) : ReadOnlyProperty<Person, HashSet<Animal>> {
        override operator fun getValue(thisRef: Person, property: KProperty<*>): HashSet<Animal> {
            println("${thisRef.name} купил $value")
        return value
    }
}