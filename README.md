# mp3Merger

---

a simple gui application that lets the user add audio files to a list and then merge these files into a single mp3 file

---

## details

this application uses scalafx with appache commons library to merge audio files



```scala
object AudioMerge {
  def merge3(files : List[String]) : Unit = {
    def loop(lst : List[String]) : SequenceInputStream =
      if lst.length == 2 then new SequenceInputStream(new FileInputStream(lst.head), new FileInputStream(lst(1)))
      else new SequenceInputStream(new FileInputStream(lst.head), loop(lst.tail))

    val seqs = loop(files)
    FileUtils.copyInputStreamToFile(seqs, new File("out.mp3"))
    seqs.close()
  }

}

```



the code above show a non-tail recursive function that merges multiple input streams into a squence input stream that will be written to file
