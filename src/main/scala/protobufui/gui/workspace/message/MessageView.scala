package protobufui.gui.workspace.message

import javafx.beans.property.{SimpleStringProperty, StringProperty}
import javafx.scene.control.ContextMenu

import ipetoolkit.workspace.{WorkspaceEntry, WorkspaceEntryView}
import protobufui.service.source.ClassesContainer.MessageClass


class MessageView(workspaceEntry: WorkspaceEntry, messageClass: MessageClass) extends WorkspaceEntryView {

  override val nameProperty: StringProperty = new SimpleStringProperty(messageClass.clazz.getName)

  override def contextMenu: Option[ContextMenu] = None

  override def model: WorkspaceEntry = workspaceEntry

  override def detailsPath: String = "/fxml/messagePane.fxml"
}
