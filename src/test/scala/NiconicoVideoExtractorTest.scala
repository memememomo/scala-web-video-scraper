import scraper._
import org.scalatest.{Matchers, FlatSpec}

class NiconicoVideoExtractorTest extends FlatSpec with Matchers {

  "collect url" should "extract movieUrl thumbnail and title" in {
    val vOp = NicoNicoVideoExtractor.extractMovie("http://www.nicovideo.jp/watch/sm1715919")

    assert(vOp.isDefined)

    val v = vOp.get
    assert(v.title == "初音ミク　が　オリジナル曲を歌ってくれたよ「メルト」")
    assert(v.movieUrl == "http://ext.nicovideo.jp/thumb_watch/sm1715919?thumb_mode=swf&amp;ap=1&amp;c=1")
    assert(v.thumbnailUrl == "http://tn-skr4.smilevideo.jp/smile?i=1715919")
  }

}
