class fenwick{
    int [] array;
    int [] c;
    fenwick(int nodes){
        this.c = new int[nodes+1];
        this.array = new int[nodes+1];
    }
    int lowbit(int posi){
        return posi&(-posi);
    }
    void update(int posi, int value){
        int dif = value - array[posi];
        array[posi] += dif;
        while(posi < c.length){
            c[posi] += dif;
            posi += lowbit(posi);
        }
    }
    int sum(int posi){
        int sum = 0;
        while(posi > 0){
            sum += c[posi];
            posi -= lowbit(posi);
        }
        return sum;
    }
    int RangeSum(int num1, int num2){
        return  sum(num2) - sum(num1-1) ; 
    }
}
