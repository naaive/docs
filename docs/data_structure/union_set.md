# Union Set


```java

class Solution {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        UnionSet<Integer> set = new UnionSet<>(integers);

        System.out.println(set.connect(1, 3));
        set.union(1, 3);
        System.out.println(set.connect(1, 3));
        System.out.println(set.connect(1, 4));
        set.union(3,4);
        System.out.println(set.connect(1, 4));

    }
}

class Node<V> {
    V val;
    Node<V> parent;

    public Node(V val) {
        this.val = val;
    }
}

class UnionSet<V> {

    private HashMap<V, Node<V>> nodes;

    public UnionSet(List<V> els) {
        nodes = new HashMap<>();
        for (V el : els) {
            Node<V> node = new Node<>(el);
            node.parent = node;
            nodes.put(el, node);
        }
    }

    public void union(V v1, V v2) {
        Node<V> v1Node = nodes.get(v1);
        Node<V> v2Node = nodes.get(v2);
        if (v1Node == null || v2Node == null) {
            throw new IllegalArgumentException();
        }
        LinkedList<Node<V>> stackV1 = new LinkedList<>();
        LinkedList<Node<V>> stackV2 = new LinkedList<>();
        Node<V> v1Root = v1Node;
        while (v1Root.parent != v1Root) {
            stackV1.push(v1Node);
            v1Root = v1Root.parent;
        }
        Node<V> v2Root = v2Node;
        while (v2Root.parent != v2Root) {
            stackV2.push(v2Node);
            v2Root = v2Root.parent;
        }

        if (stackV1.size() < stackV2.size()) {
            while (!stackV1.isEmpty()) {
                Node<V> pop = stackV1.pop();
                pop.parent = v2Root;
            }
            v1Root.parent = v2Root;

        } else {
            while (!stackV2.isEmpty()) {
                Node<V> pop = stackV2.pop();
                pop.parent = v1Root;
            }
            v2Root.parent = v1Root;
        }

    }

    public boolean connect(V v1, V v2) {
        return findParent(v1) == findParent(v2);
    }

    private Node<V> findParent(V v) {
        Node<V> vNode = nodes.get(v);
        if (vNode == null) {
            return null;
        }
        return vNode.parent;
    }
}
```