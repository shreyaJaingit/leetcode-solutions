class Solution {

    int n, k;
    int[][] cnt;
    int[] prod;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        // Segment tree
        cnt = new int[4 * n][k];
        prod = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            update(1, 0, n - 1, index, value % k);

            // Query [start, n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[q] = res.cnt[x];
        }

        return ans;
    }

    // --------------------------------------------------
    // BUILD
    // --------------------------------------------------

    void build(int node, int l, int r, int[] nums) {

        if (l == r) {

            prod[node] = nums[l] % k;

            // The only non-empty prefix is the element itself
            cnt[node][prod[node]] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        merge(node);
    }

    // --------------------------------------------------
    // MERGE TWO CHILDREN
    // --------------------------------------------------

    void merge(int node) {

        int left = node * 2;
        int right = node * 2 + 1;

        // Product of the whole segment
        prod[node] = (prod[left] * prod[right]) % k;

        // Clear current counts
        for (int r = 0; r < k; r++) {
            cnt[node][r] = 0;
        }

        // 1. Prefixes completely inside LEFT
        for (int r = 0; r < k; r++) {
            cnt[node][r] += cnt[left][r];
        }

        // 2. LEFT + prefix of RIGHT
        for (int r = 0; r < k; r++) {

            int newRemainder = (prod[left] * r) % k;

            cnt[node][newRemainder] += cnt[right][r];
        }
    }

    // --------------------------------------------------
    // POINT UPDATE
    // --------------------------------------------------

    void update(int node, int l, int r, int index, int value) {

        if (l == r) {

            prod[node] = value;

            for (int x = 0; x < k; x++) {
                cnt[node][x] = 0;
            }

            cnt[node][value] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        merge(node);
    }

    // --------------------------------------------------
    // RANGE QUERY
    // --------------------------------------------------

    Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {

            Node res = new Node(k);

            res.prod = prod[node];

            for (int x = 0; x < k; x++) {
                res.cnt[x] = cnt[node][x];
            }

            return res;
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        // Query overlaps both children
        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return combine(left, right);
    }

    // --------------------------------------------------
    // COMBINE TWO QUERY RESULTS
    // --------------------------------------------------

    Node combine(Node left, Node right) {

        Node res = new Node(k);

        res.prod = (left.prod * right.prod) % k;

        // Prefixes entirely in LEFT
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }

        // LEFT + prefix of RIGHT
        for (int r = 0; r < k; r++) {

            int newRemainder = (left.prod * r) % k;

            res.cnt[newRemainder] += right.cnt[r];
        }

        return res;
    }

    // --------------------------------------------------
    // NODE CLASS
    // --------------------------------------------------

    class Node {

        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }
}