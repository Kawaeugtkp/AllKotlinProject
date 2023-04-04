package jp.gardenall.kotlinbasics

open class Base(){
    var a = 1 // public by default
    private var b = 2 // private to Base class
    protected open val c = 3 // visible to the Base and the Derived class
    internal val d = 4 // visible inside the same module
    protected fun e(){ } // visible to the Base and the Derived class
}

class Derived: Base(){
    // a, c, d and e() of the Base class are visible
    // b is not visible
    override val c = 9
}

fun main(args: Array<String>){
    val base = Base()
    // base.a and base.d are visible
    // base.b, base.c and base.e() are not visible
    val derived = Derived()
    // derived.c is not visible
}

// try: the try block contains a set of statements which might generate an exception.
// It must be followed by either catch or finally or both.
// catch: the catch block is used to catch the exception thrown from try block.
// finally: finally block always execute whether exception is handled or not.
// So it is used to execute important code statement.(like closing buffers)
// throw: the throw keyword is used to throw an exception explicitly.