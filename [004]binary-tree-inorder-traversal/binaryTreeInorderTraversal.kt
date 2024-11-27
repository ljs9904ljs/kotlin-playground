/*
Binary Tree Inorder Traversal

*/

class TreeNode(
    var value: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

fun inorderTraversal(t: TreeNode?): List<Int> {
    if (t == null) return emptyList()
    
    return inorderTraversal(t.left) + listOf(t.value) + inorderTraversal(t.right)
}

class TestInorderTraversal {
    private fun testInorderTraversal1() {
        val t1 = TreeNode(1)
        val t2 = TreeNode(2)
        val t3 = TreeNode(3)
        t1.left = t2
        t1.right = t3
        
        val result = inorderTraversal(t1)
        
        assert(result == listOf(2, 1, 3)) {
            "Wrong inorder traversal."
        }
    }
    
    private fun testInorderTraversal2() {
        val t1 = TreeNode(1)
        val t2 = TreeNode(2)
        val t3 = TreeNode(3)
        t1.right = t2
        t2.left = t3
        
        val result = inorderTraversal(t1)
        
        assert(result == listOf(1, 3, 2)) {
            "Wrong inorder traversal."
        }
    }
    
    private fun testInorderTraversal3() {
        val t1 = TreeNode(1)
        val t2 = TreeNode(2)
        val t3 = TreeNode(3)
        t1.right = t2
        t2.right = t3
        
        val result = inorderTraversal(t1)
        
        assert(result == listOf(1, 2, 3)) {
            "Wrong inorder traversal."
        }
    }
    
    private fun testInorderTraversal4() {
        val t1 = TreeNode(1)
        val t2 = TreeNode(2)
        val t3 = TreeNode(3)
        val t4 = TreeNode(4)
        val t5 = TreeNode(5)
        val t6 = TreeNode(6)
        val t7 = TreeNode(7)
        
        t1.left = t2
        t1.right = t3
        t2.left = t4
        t2.right = t5
        t5.left = t6
        t5.right = t7
        
        val result = inorderTraversal(t1)
        
        assert(result == listOf(4, 2, 6, 5, 7, 1, 3)) {
            "Wrong inorder traversal."
        }
    }
    
    fun test() {
        val tests = listOf(
            ::testInorderTraversal1,
            ::testInorderTraversal2,
            ::testInorderTraversal3,
            ::testInorderTraversal4
        )
        
        tests.forEach { it() }
        println("All tests passed.")
    }
}

fun main(args: Array<String>) {
    TestInorderTraversal().test()
}