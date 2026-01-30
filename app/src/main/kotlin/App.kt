package org.cobalt.app


class MyLinkedList<T> {
    private var headNode: Node<T> = Node(null)

    fun get(i: Int): T? {
        var curNode: Node<T>? = headNode.getNextNode()
        var currentIndex = 0
        while (currentIndex != i) {
            if (curNode?.getData() == null) throw IllegalArgumentException("No element for index ${i}")
            currentIndex++
            curNode = curNode.getNextNode()
        }

        return curNode?.getData()
    }

    fun removeDuplicates(): MyLinkedList<T> {
        val newList = MyLinkedList<T>()
        var newListCurrentNode = newList.headNode

        var curMainListNode: Node<T>? = headNode.getNextNode()
        var prevElementValue: T? = null
        while (curMainListNode != null) {
            if (curMainListNode.getData() != prevElementValue) {
                val newNode = Node<T>(curMainListNode.getData())
                newListCurrentNode.setNextNode(newNode)
                newNode.setPrevNode(newListCurrentNode)
                newListCurrentNode = newNode
            }
            prevElementValue = curMainListNode.getData()
            curMainListNode = curMainListNode.getNextNode()
        }

        return newList
    }

    override fun toString(): String {
        var result = "["
        var curNode: Node<T>? = headNode.getNextNode()
        while (curNode != null) {
            result += if (headNode.getNextNode() == curNode) {
                "${curNode.getData()}"
            } else {
                ", ${curNode.getData()}"
            }
            curNode = curNode.getNextNode()
        }
        return "$result]"

    }

    constructor(vararg elements: T) {
        var currNode = headNode
        for (element in elements) {
            var newNode = Node(element)
            currNode.setNextNode(newNode)
            newNode.setPrevNode(currNode)
            currNode = newNode
        }
    }

    //Класс узла связного списка, помещен внутрь класса списка для изоляции от вмешательства пользователя списка
    private class Node<T>(private val data: T?) {
        private var nextNode: Node<T>? = null
        private var prevNode: Node<T>? = null

        fun setPrevNode(node: Node<T>) {
            this.prevNode = node
        }

        fun setNextNode(node: Node<T>) {
            this.nextNode = node
        }

        fun getNextNode(): Node<T>? = nextNode
        fun getPrevNode(): Node<T>? = nextNode

        fun getData(): T? = data
    }
}


fun main() {
    val testList = MyLinkedList<Int>(1, 1, 2, 3, 3, 3)
    val afterRemoveDuplicates = testList.removeDuplicates()
    assertEquals(afterRemoveDuplicates.get(0), 1)
    assertEquals(afterRemoveDuplicates.get(1), 2)
    assertEquals(afterRemoveDuplicates.get(2), 3)
    println(afterRemoveDuplicates.toString())
}

fun <T> assertEquals(element1: T, element2: T) {
    if (element1 != element2) throw IllegalArgumentException("Elements doesn't match: ${element1} ${element2}")
}
