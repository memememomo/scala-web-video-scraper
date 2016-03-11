package scraper

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.slf4j.LoggerFactory


object NicoNicoVideoExtractor extends MovieScraper {

  val logger = LoggerFactory.getLogger(getClass)

  override def extractMovie(url: String): Option[MovieScrapingData] = {
    if (isNicoNicoUrl(url)) {
      extract(url)
    } else if (isNicoNicoVideoUrl(url)) {
      Some(MovieScrapingData(ServiceType.NicoNico, url, "Movie on NicoVideo", ""))
    } else {
      None
    }
  }

  def isNicoNicoUrl(url: String) = {
    url.startsWith("http://www.nicovideo.jp/watch/")
  }

  def isNicoNicoVideoUrl(url: String) = {
    url.startsWith("http://ext.nicovideo.jp/")
  }


  val videoRegex = """<meta property="og:video" content="(.+?)">""".r
  val titleRegex = """<meta property="og:title" content="(.+?)">""".r
  val thumbnailRegex = """<meta property="og:image" content="(.+?)">""".r


  def extract(url: String) = {
    val httpClient = HttpClients.createDefault()
    val request = new HttpGet(url)

    val response = httpClient.execute(request)

    val site = new String(EntityUtils.toString(response.getEntity, "UTF-8"))
    videoRegex.findFirstMatchIn(site) match {
      case Some(m) => {
        val url = m.group(1)
        val title = Decoder.decodeHtmlReference(titleRegex.findFirstMatchIn(site).map(_.group(1)).getOrElse(""))
        val thumbnail = thumbnailRegex.findFirstMatchIn(site).map(_.group(1)).getOrElse("")
        Some(MovieScrapingData(ServiceType.NicoNico, url, title, thumbnail))
      }
      case None => {
        logger.info(s"Fail to extract movie url from ${url}")
        None
      }
    }
  }
}
