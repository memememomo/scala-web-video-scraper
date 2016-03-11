package scraper

trait MovieScraper {
  def extractMovie(url: String): Option[MovieScrapingData]
}

case class MovieScrapingData(serviceType: ServiceType.Value, movieUrl: String, title: String, thumbnailUrl: String)
