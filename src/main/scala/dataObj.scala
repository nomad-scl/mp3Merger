import javafx.collections.ObservableList
import javafx.collections.FXCollections as jfx
import scalafx.beans.property.BooleanProperty
import scalafx.beans.property.IntegerProperty
import scalafx.beans.property.ObjectProperty
import java.util.List as jlist

class dataObj(private val mp3List : ObjectProperty[ObservableList[String]] =

              ObjectProperty[ObservableList[String]](jfx.observableList(jlist.of[String]())),
             
              private val sortAlphabetically : BooleanProperty = BooleanProperty(true),
              private val selectedIndex : IntegerProperty = IntegerProperty(-1)) {

  sortAlphabetically.onChange((v1,v2,v3) => {
    if v1.value && (mp3List.value.size() > 0) then {
      this.setMp3List(mp3List.value.sorted().toArray.map(_.toString).toList)
    }
  })
  def getMp3List : ObjectProperty[ObservableList[String]] = this.mp3List
  def getSorted : BooleanProperty = this.sortAlphabetically
  def getIndex: IntegerProperty = this.selectedIndex

  def setMp3List(newList : List[String]) : Unit = this.mp3List.value = jfx.observableList(jlist.of[String](newList:_*))
  def setSorted(newSort : Boolean) : Unit = this.sortAlphabetically.value = newSort
  def setIndex(newIndex : Int) : Unit = this.selectedIndex.value = newIndex
}
