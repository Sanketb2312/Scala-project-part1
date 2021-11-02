object Task2 extends App {
  
  // a)
  def createThread(body: => Unit): Thread = {
    val thread = new Thread {
      override def run() = body
    }

    thread
  }

  // b, c) 
  private var counter: Int = 0
  def increaseCounter(): Unit = this.synchronized {
    counter += 1
  }

  def printCounter(): Unit = {
    println(counter)
  }

  val t1 = createThread(increaseCounter)
  val t2 = createThread(increaseCounter)
  val t3 = createThread(printCounter)

  t1.start()
  t2.start()
  t3.start()

  // d)
  lazy val a: Int = {
    def useA(): Unit = {
      println(a)
    }

    val locked = createThread(useA)
    locked.start()
    locked.join()

    10
  }

  println(a + 10)
}
