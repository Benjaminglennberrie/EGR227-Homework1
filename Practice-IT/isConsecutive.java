



public boolean isConsecutive(Stack<Integer> p) {

    if(p.size() < 2)
        return true;

    Queue<Integer> q = new LinkedList<Integer>();
    int next = p.pop();
    q.add(next);

    boolean consecutive = true;

    while(!p.isEmpty()) {
        int n = p.pop();

        if(n + 1 != next)
            consecutive = false;

        next = n;
        q.add(n);
    }

    while(!q.isEmpty())
        p.push(q.remove());

    while(!p.isEmpty())
        q.add(p.pop());

    while(!q.isEmpty())
        p.push(q.remove());

    return consecutive;
}