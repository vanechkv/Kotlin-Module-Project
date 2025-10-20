import java.text.SimpleDateFormat
import java.util.Date
import java.util.Scanner

class App {
    private val scanner = Scanner(System.`in`)
    private val archives: MutableList<Archive> = mutableListOf()

    fun start() {
        archivesMenu()
    }

    private fun archivesMenu() {
        val menu = Menu("Список архивов:")
        menu.addOption("Создать архив") { makeArchive() }
        archives.forEachIndexed { index, archive ->
            menu.addOption(archive.title) { notesMenu(archive) }
        }
        menu.addOption("Выход") { exit() }
        menu.show()
    }

    private fun makeArchive() {
        println("Введите название архива")
        val title = scanner.nextLine().trim()
        if (title.isNotEmpty()) {
            archives.add(Archive(title))
            println("Архив $title создан")
        } else println("Название архива не может быть пустым")
        archivesMenu()
    }

    private fun notesMenu(archive: Archive) {
        val menu = Menu(archive.title)
        menu.addOption("Создать заметку") { makeNote(archive) }
        archive.notesList.forEachIndexed { index, note ->
            menu.addOption(note.title) { showNote(note, archive) }
        }
        menu.addOption("Назад") { archivesMenu() }
        menu.show()
    }

    private fun makeNote(archive: Archive) {
        println("Введите название заметки")
        val title = scanner.nextLine().trim()
        if (title.isNotEmpty()) {
            println("Введите содержание заметки")
            val text = scanner.nextLine().trim()
            if (text.isNotEmpty()) {
                archive.notesList.add(Note(title, text, Date()))
                println("Заметка $title создана")
            } else println("Содержание заметки не может быть пустым")
        } else println("Название заметки не может быть пустым")
        notesMenu(archive)
    }

    private fun showNote(note: Note, archive: Archive) {
        val menu = Menu("")
        val formDate = SimpleDateFormat("dd.MM.yy")
        println("${note.title}\n${note.text}\n${formDate.format(note.date)}")
        menu.addOption("Назад") { notesMenu(archive) }
        menu.show()
    }

    private fun exit() {
        println("Выход из приложения")
    }
}