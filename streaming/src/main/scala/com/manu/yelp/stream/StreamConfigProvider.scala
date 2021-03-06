package com.manu.yelp.stream

import java.net.URL
import java.util.Properties

import scala.io.Source

object StreamConfigProvider {

  var bootstrapServers: String = _
  var topicList: String = _
  var acks: String = _
  var numRetries: Int = _
  var batchSize: Int = _
  var linger_ms: Int = _
  var buffer_memory: Long = _
  var key_serializer: String = _
  var value_serializer: String = _

  private var props: Properties = _

  def setConfig(path: String): Unit = {
    val configFile: URL = getClass.getResource(path)

    if(!configFile.equals(null)) {
      val source = Source.fromURL(configFile)
      props = new Properties()
      props.load(source.bufferedReader())
    } else {
      System.exit(1)
    }

    bootstrapServers = props.getProperty("bootstrapServers")
    topicList = props.getProperty("topicList")
    acks = props.getProperty("acks")
    numRetries = props.getProperty("retries").toInt
    batchSize = props.getProperty("batchSize").toInt
    linger_ms = props.getProperty("linger_ms").toInt
    buffer_memory = props.getProperty("buffer_memory").toLong
    key_serializer = props.getProperty("key_serializer")
    value_serializer = props.getProperty("value_serializer")

  }
}
