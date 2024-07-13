import AudioMerge.merge3
import scalafx.Includes.observableList2ObservableBuffer
import scalafx.stage.FileChooser
import javafx.collections.FXCollections as jfx

import java.util.List as jlist

object buttonEvents {

  def mergeButton(data : dataObj) : Unit =
    if data.getMp3List.value.nonEmpty then merge3(data.getMp3List.value.toList)

  def addButton(data : dataObj): Unit = {
    val chooser = new FileChooser{
      title = "Select file or folder"
      extensionFilters ++= Seq(new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"))
    }
    val selectedFiles = chooser.showOpenMultipleDialog(null)
    if selectedFiles != null then {
      val h = data.getMp3List.value.toSeq ++ selectedFiles.map(_.toString)
      data.setMp3List(h.toList)
      data.setSorted(false)
      data.setIndex(0)
    }
  }

  def clearButton(data: dataObj): Unit = {
    data.getMp3List.value = jfx.observableList(jlist.of[String]())
    data.setIndex(-1)
  }

  def downButton(data: dataObj): Unit = {
    if data.getMp3List.value.nonEmpty then {
      val newInd = (data.getIndex.value + 1) % data.getMp3List.value.length

      val lst = data.getMp3List.value.map(_.toString).toArray

      val tmp = lst(data.getIndex.value)
      lst(data.getIndex.value) = lst(newInd)
      lst(newInd) = tmp

      data.setSorted(false)
      data.setMp3List(lst.toList)
      data.setIndex(newInd)
    }
  }

  def upButton(data: dataObj): Unit = {
    if data.getMp3List.value.nonEmpty then {
      val newInd = if data.getIndex.value < 1 then data.getMp3List.value.length - 1 else data.getIndex.value - 1

      val lst = data.getMp3List.value.map(_.toString).toArray

      val tmp = lst(data.getIndex.value)
      lst(data.getIndex.value) = lst(newInd)
      lst(newInd) = tmp

      data.setSorted(false)
      data.setMp3List(lst.toList)
      data.setIndex(newInd)
    }
  }

  def rmCurButton(data: dataObj): Unit = {
    if data.getIndex.value >= 0 then {
      val lst = data.getMp3List.value.toList
      val nl = lst.patch(data.getIndex.value, Nil, 1)
      data.setMp3List(nl)
      data.setIndex(data.getIndex.value - 1)
      println(data.getIndex)
    }
  }
}
