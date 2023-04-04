package jp.gardenall.kotlinbasics

import android.webkit.WebStorage.Origin
import kotlin.math.floor

data class User(val id: Long, val name: String)

class MobilePhone(osName: String, brand: String, model: String){
    var battery: Int = 20
    init {
        println("The phone $model from $brand uses $osName as its Operating System") //クラスの引数はinit内ならこういう風の$のあとに使うことができる。
    }
    fun stateHobby(){
        println("${battery + 30}\'s now battery")
    }
}

abstract class Mammal(private val name: String, private val origin: String, private val weight: Double){

    // Abstract Property (Must be overridden by Subclasses)
    abstract var maxSpeed: Double

    // Abstract Methods (Must be implemented by Subclasses)
    abstract fun run()
    abstract fun breath()

    // Concrete (Non Abstract) Method
    fun displayDetails(){
        println("Name: $name, Origin: $origin, Weight: $weight, Max Speed: $maxSpeed")
    }
}

class Human(name: String, origin: String, weight: Double, override var maxSpeed: Double): Mammal(name, origin, weight){
    override fun run() {
        // Code to run
        println("Runs on two legs")
    }

    override fun breath() {
        // COde to breath
        println("Breath through mouth or nose")
    }
}

class Elephant(name: String, origin: String, weight: Double, override var maxSpeed: Double): Mammal(name, origin, weight){
    override fun run() {
        println("Runs on four legs")
    }

    override fun breath() {
        println("Breath through the trunk")
    }
}

data class Fruit(val name: String, val price: Double)

fun main(){



//    val human = Human(name = "Denis", origin = "Russia", 70.0, maxSpeed = 28.0)
//    val elephant = Elephant(name = "Rosy", origin = "India", weight = 5400.0, maxSpeed = 25.0)
//
//    val stringList: List<String> = listOf("Denis", "Frank", "Michael", "Garry")
//    val mixedTypeList: List<Any> = listOf("Denis", 31, 5, "BDay", 70.500043, "weighs", "Kg")
//
//    for (value in mixedTypeList){
//        if (value is Int){
//
//        }else if (value is Double){
//            println("Double: $value with floor value ${floor(value)}")
//        }else if (value is String){
//
//        }else{
//            println("Unknown Type")
//        }
//    }

//    for (value in mixedTypeList){
//        when(value){
//            is Int -> println("")
//            is Double -> println("Double: $value with floor value ${floor(value)}") //floorはどうやら切り捨てて整数にすることみたい。出力結果としては小数点以下が何位であっても1位を切り捨てして表記としては少数第一位の0までを出力している
//            is String -> println("")
//            else -> println("Unknown Type")
//        }
//    }

//    val obj2: Any = 1337
//    val str2: String = obj2 as String
//    println(str2) // obj2がString型ではないので変換できない

    val obj3: Any = 1337
    val str3: String? = obj3 as? String
    println(str3)

//    val numbers: IntArray = intArrayOf(1, 2, 3, 4, 5, 6)
//    val numbers = intArrayOf(1, 2, 3, 4, 5, 6)
    val numbers = arrayOf(1, 2, 3, 4, 5, 6) // 今回は要素が全てIntであることが明らかなので、
    // numbers: IntArrayとすることも、intArrayOf()とする必要もない

    print(numbers.contentToString()) // 単にprint(numbers)とすると住所みたいなのが出る。
//    for(element in numbers){
//        print(" ${element + 2}")
//    }
//    print(numbers[0]) // これについてはcontentToString()を付けずともちゃんと値が出る
    numbers[0] = 21 // こういうこともできる
//
//    val fruits = arrayOf(Fruit(name = "Apple", price = 2.5), Fruit(name = "Grape", price = 3.5))
//    print(fruits.contentToString())
//    for (fruit in fruits){
//        println(fruit.name)
//        println(" ${fruit.name}")
//    }
//    for (index in fruits.indices){
//        print("${fruits[index].name} is in index $index")
//    }
//
//    val days = arrayOf("Sun", "Mon", 1, 2, Fruit(name = "Lemon", price = 2.0))
//
//    val months = listOf("January", "February", "March") //listはimmutabledからmutableにするためには別のpropertyを作る必要がある。
//    val anyTypes = listOf(1, 2, 3, true, false, "String")
////    println(anyTypes.size)
////    println(months[1])
//
//    for (month in months){
//        println(month)
//    }
//
//    val additionalMonths = months.toMutableList()
//    val newMonths = listOf("April", "May", "June") //instractorはここでlistOfではなくarrayOfを使っていた。その真意は不明。今のところはlistOfでやりたいことが機能している。
//    additionalMonths.addAll(newMonths)
//    println(additionalMonths)
//
//    val dayss = mutableListOf<String>("Mon", "Tue", "Wed") //listの要素の型を指定している<String>は無くても問題なく動いているようだ。
//    dayss.add("Thu")
//    dayss[2] = "Sunday"
//    dayss.removeAt(3)
//    val removeList = mutableListOf<String>("Mon", "Tue")
//    dayss.removeAll(removeList) //removeAllは消したいもののリストを先に別で作成してそれを引数に通すことで使える。dayssを全消ししたいと思えば引数にdayssを持ってきたらいい
//    print(dayss)

//    human.run()
//    elephant.run()
//
//    human.breath()
//    elephant.breath()

//    val fruits = setOf("Orange", "Apple", "Mango", "Grape", "Apple", "Orange")
//    val newFruits = fruits.toMutableSet() //ここをinstractorはtoMutableListにしていたが、今のところtoMutableSetで問題なし。
//    newFruits.add("Water Melon")
//    print(newFruits)
//    print(newFruits.elementAt(4))

    val daysOfTheWeek = mapOf("10" to "Monday", "2" to "Tuesday", "3" to "Wednesday")
//
//    for (key in daysOfTheWeek.keys){
//        print("$key is to ${daysOfTheWeek[key]}")
//    }

    val fruitsMap = mapOf("Favorite" to Fruit("Grape", 2.5), "OK" to Fruit("Apple", 1.0))

    val newDaysOfWeek = daysOfTheWeek.toMutableMap()
    newDaysOfWeek["4"] = "Thursday"

    print(newDaysOfWeek.toSortedMap())

    val myArrayList: ArrayList<Double> = ArrayList()
    myArrayList.add(13.324321)
    myArrayList.add(54.234321)
    myArrayList.add(76.234543)
    myArrayList.add(97.123456)
    myArrayList.add(31.127654)
    var total = 0.0
    for (i in myArrayList){
        total += i
    }
//    var average = total / myArrayList.size
//    println("Average is $average")
//
//    val sum = {a: Int, b: Int -> a + b}
//    println(sum(12, 54))


//    val user1 = User(id = 1, name = "Denis")
//    var myName = "Denis"

//    val updatedUser = user1.copy(name = "Tatsu")
//    val udpatedToUser = user1.copy()
//    val updatedUser = user1
//
//    println(user1)
//    println(updatedUser)
//    println(udpatedToUser)
//
//    println(updatedUser.component1())
//    println(updatedUser.component2())

//    val (updatedId, updatedame) = updatedUser
//    println("id = $updatedId, name = $updatedame")

    // Integer TYPES: Byte (8 bit), Short (16 bit), Int (32 bit), Long (64 bit)
    val myByte: Byte = 13 //ちょこちょこやって試したら128からダメやった
    val myShort: Short = 125
    val myInt: Int = 123123123
    val myLong: Long = 12_039_812_309_487_120_3

    //シングルクオテーションはchrなので、その中に複数文字を入れることはできない
    var char = 'a'

    var resultDouble: Double
    resultDouble = 5.0/3

//    print("Hello " + myName)
//    print("hello")
//    println("hello") //printlnはそれを出力した後改行する。つまりこのコードを書いているhelloが出力された後改行され、次の1行下のhelloは下になる
//    print("hello")
//    print("hello")
//    println("hello")
//    println("hello")

    //Increment & Decrement operators (++ --)

    var isRainy = true

    //これでもとりあえず機能している
//    if(isRainy)
//        print("isRainy is $isRainy")

//    var season = 3
//    when(season){
//        1 -> println("spring")
//        2 -> println("summer")
//        3 -> {
//            println("fall")
//            println("autumn")
//        }
//        4 -> println("winter")
//        else -> println("Invalid season")
//    }

//    var age = 31
//
//    when(age){
//        in 21..150 -> println("now you may drink in the US")
//        in 18..20 -> println("you may vote now")
//        16,17 -> println("you may drive now")
//        else -> println("you're too young")
//    }

//    var z: Any = 13.37
//    when(z){
//        !is Int -> println("$z is not an Int")
//        is Double -> println("$z is a Double")
//        is String -> println("$z is a String")
//        else -> println("$z is none of the above")
//    }

//    var x = 1
//    while (x <= 10) {
//        print("$x")
//        x++
//    }
//    println("\nWhile loop is done")
//
//    x = 1
//    do{
//        println("$x")
//        x++
//    }while (x <= 10)
//    println("\nDo while loop is done")
//
//    for (num in 1..10){
//        print("$num")
//    }
//
//    for (i in 1 until 10){
//        print("$i ")
//    }

    println("\n-----------------------")

    for (i in 10 downTo 1){
        print("$i ")
    }

    println("\n-----------------------")

    for (i in 10 downTo 1 step 2){
        print("$i ")
    }
    println("\n-----------------------")
    for (i in 10.downTo(1).step(2)){
        print("$i ")
    }

    var a = 0
    while (a <= 10000){
        a++
        if (a == 9001){
            print("IT'S OVER 9000!!!")
        }
    }

    var humidityLevel = 80
    var humidity = "humid"
    for (i in 1 until 20){ //これは1以上20未満
        print("$i ")
        if (i/2 == 5){
            break
        }
    }
    print("Done with the loop")

    for (i in 1 until 20){ //これは1以上20未満
        if (i/2 == 5){
            continue //continueはここに当てはまったときにそのループの一回は終わって次のループに入ってくる
        }
        print("$i ")
    }

    print("\nresult is ${addUp(5,3)}")

    var name: String = "Denis"
    var nullableName: String? = "Denis"
//    nullableName = null

    var len = name.length
    var len2 = nullableName?.length //このコードは下のコードでlen3を出力するのと同じ意味をさしている

    print(nullableName?.toLowerCase()) //toLowerCaseは事前に対象となっているインスタンス（今回ならnullableName）がnullになっているとエラーになる。

    nullableName?.let { println(it.length) } //このコードも対象となっているオプショナル（nullableName）がnullになっている宣言があるとエラーとなる。

    if (nullableName != null){
        var len3 = nullableName.length //swiftと違って、このif文を通したnullableNameは既にオプショナルではなくなっているみたい。
    }else{
        null
    }

    var newName = nullableName ?: "Guest"

    println(nullableName!!.toLowerCase()) //これがKotlin版force unwrapping

    var denis = Person("Denis", "Panjuta", age=31) //age追加したけどage:っていう表記ではダメみたいです
    denis.hobby = "skateboard"
    denis.stateHobby()
    var john = Person() //引数のdeafult valueを設定しているから、引数の型がoptionalでなくてもこのobjectの設定の仕方でエラーが起きない。
    var anotherJohn = Person(lastName = "Peterson")
    anotherJohn.age = 12

    var myCar = Car()
    myCar.maxSpeed = 1 //0以下の値を設定するとエラーが発生する
    println("max speed is ${myCar.maxSpeed}")

    var myCarr = Cars(maxSpeed = 200.0, name = "A3", brand = "Audi")
    var myECar = ElectricCar(maxSpeed = 240.0, name = "S-Model", brand = "Tesla", batteryLife = 85.0)

    myCarr.drive(distance = 200.0)
    myECar.drive(distance = 200.0)
}
// Method: a Method is a Function within a class. つまり下のaddUpは一応メソッドとは違うようです
fun addUp(a: Int, b: Int): Int {
    return a+b
}

class Person(firstName: String = "John", lastName: String = "Doe"){ //この二つってinitializerで通す型ってこと？つまりswiftではclassないで引数も型も定義していたけどここでinitializerに関してはpropertyの型も含めて定義してしまっているということな気がす
    // Member Variables - Properties
    var age: Int? = null
    var hobby: String = "watch Netflix" //propertiesはとにかく初期値を一般のものもoptionalも設定しないといけない（nullを含む）。つまり、下のコードのように型だけ設定みたいなのはエラー
    var firstName: String? = null //classの引数で持ってきたものは直接使えないからpropertyを別で作ってそこにinit内（ここは別の場所でもいいかもしれんけど）でpropertyの値として設定しないといけない
//    var color: String
//    var color: String?

    // Initializer Block
    init {
        this.firstName = firstName
        println("Initialized a new Person object with firstName = $firstName and lastName = $lastName")
    }

    // Member secondary Constructor
    constructor(firstName: String, lastName: String, age: Int): this(firstName, lastName){
        this.age = age
        println("Initialized a new Person object with firstName = $firstName and lastName = $lastName and age $age")
    } //この中の実行は、今回ならageがinitializer的な感じで引数設定がされた場合にのみ呼び出されるものである

    // Member functions - Methods
    fun stateHobby(){
        println("$firstName\'s hobby is $hobby")
    }
}

class Car(){
    lateinit var owner: String //これでpropertyの初期値を設定しなくてもproperty作成ができる

    val myBrand: String = "BMW"
        //このgetの上は勝手にインデントされていて、getがmyBrandに付随したものであるということがわかる
    get() {
        return field.toLowerCase()
    }

    var maxSpeed: Int = 250
    get() = field
    set(value) {
        field = if (value > 0) value else throw IllegalArgumentException("Maxspeed cannot be less than 0") // maxSpeedの中に0以下を入れたらエラー出るけど、エラーメッセージの中にこの括弧内のメッセージが表示される
    } //こうやってgetterとsetterが設定できる。ただ今回のgetterとsetterはおそらくdefaultのものなので何も設定しなかった場合と動きが変わらない。

    var myModel: String = "M5"
    private set

    init {
        this.owner = "Frank" //initialzerで上のlateinitの初期値設定をしなくてもエラーは起きないが、結局propertyは使うために作成するのでおそらく大体はinit内で初期値設定をする
    }
}

fun myFunction(a: Int){
    var a = a //関数の引数として使ったa（右側）と同じ文字を使って関数内でインスタンスを作ることができる。つまり、イコールの左右のaは違うものである。
    println("a is $a") //ここのprintlnでは関数内で定義したa（上の式の左側）がprintされる
}

interface Drivable {
    val maxSpeed: Double
    fun drive(): String //このstringは関数の戻り値の指定らしい
    fun brake(){
        println("The drivable is braking")
    }
}

open class Cars(override val maxSpeed: Double, val name: String, val brand: String): Drivable{ //このクラスを引き継ぐためにはopenをつけなければいけない。sealedを最初に付けると明示的に引き継げませんよっていうことを示すことができるみたい、これは実践できていないのでよくわからない
    open var range = 0.0 //overrideできるようにするためにはここをopenにしていないといけない

    fun extendRange(amount: Double) {
        if(amount > 0)
            range += amount
    }
    // override fun drive(): String = "driving the interface drive"
    override fun drive(): String{
        return  "driving the interface drive"
    }

    open fun drive(distance: Double){
        println("Drove for $distance KM")
    }
}

class ElectricCar(maxSpeed: Double, name: String, brand: String, batteryLife: Double) : Cars(maxSpeed, name, brand){
    override var range = batteryLife * 6

    override fun drive(distance: Double) {
        println("Drove for $distance KM on electricity")
    }

    override fun drive(): String{ //関数名が同じでも引数が同じでなければ大丈夫。
        return "Drove for $range KM on electricity"
    }

    override fun brake(){
        super.brake()
    }
}