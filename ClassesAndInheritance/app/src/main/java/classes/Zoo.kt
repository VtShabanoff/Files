package classes

import parent_classes.Animal

class Zoo {
    private val randNameForDog
            get() = listOf("Тузик", "Шарик", "Ардольд", "Агат", "Мухтар", "Жучка").random()
    private val randNameForBird
            get() = listOf("Птица - Абель", "Птица - Бетси", "Птица - Вайола",
            "Птица - Гайда", "Птица - Гизель", "Птица - Инга").random()
    private val randNameForFish
            get() = listOf("Рыба - Аква", "Рыба - Аврора", "Рыба - Афродита",
            "Рыба - Багира", "Рыба - Льдинка", "Рыба - Ванесса").random()
    private val randNameForAny
            get() = listOf("Лев - Лео", "Тигр - Бархан", "Жираф - Лонг",
            "Слон - Бобо", "Медведь - Миша", "Волк - Вольф").random()
    val listZoo = mutableListOf<Animal>().apply {
        this.add(Dog(5, randNameForDog, 10, 15))
        this.add(Dog(5, randNameForDog, 10, 15))
        this.add(Fish(1, randNameForFish, 5, 10))
        this.add(Fish(1, randNameForFish, 5, 10))
        this.add(Fish(1, randNameForFish, 5, 10))
        this.add(Bird(2, randNameForBird, 8, 30))
        this.add(Bird(2, randNameForBird, 8, 30))
        this.add(Bird(2, randNameForBird, 8, 30))
        this.add(Bird(2, randNameForBird, 8, 30))
        this.add(Bird(2, randNameForBird, 8, 30))
        this.add(object : Animal(3, randNameForAny, 5){
            override val maxAge: Int = 20

        })
        this.add(object : Animal(3, randNameForAny, 5){
            override val maxAge: Int = 20

        })
        this.add(object : Animal(3,randNameForAny, 5){
            override val maxAge: Int = 20

        })

    }

}