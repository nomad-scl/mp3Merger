import javax.sound.sampled.AudioFileFormat
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import java.io.{File, FileInputStream, InputStream, SequenceInputStream}
import java.io.File
import org.apache.commons.io.FileUtils
import org.apache.commons.vfs2.FileObject
import org.apache.commons.vfs2.FileSystemManager
import org.apache.commons.vfs2.VFS


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
