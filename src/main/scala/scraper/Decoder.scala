package scraper

object Decoder {
  def decodeHtmlReference(string: String): String = {
    val stringWithoutHexHR = "&#x(.+?);".r.replaceAllIn(string, m => Integer.parseInt(m.group(1), 16).toChar.toString)
    return "&#(.+?);".r.replaceAllIn(stringWithoutHexHR, m => Integer.parseInt(m.group(1), 10).toChar.toString)
  }

  def decodeUnicode(string: String): String = {
    return """\\u(....)""".r.replaceAllIn(string, m => Integer.parseInt(m.group(1), 16).toChar.toString)
  }
}
