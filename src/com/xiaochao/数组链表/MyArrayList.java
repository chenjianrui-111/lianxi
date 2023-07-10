package com.xiaochao.数组链表;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyArrayList<E> {
    //真正存储数据
    private E[] data;
    //记录当前数组中元素的个数
    private int size;
    //默认初始容量
    private static final int INIT_CAP = 1;

    public MyArrayList(){ this(INIT_CAP);}

    public MyArrayList(int initCapacity){
        data = (E[]) new Object[initCapacity];
        size = 0;
    }

    /***** 增 *****/

    //在数组尾部添加一个元素 e
    public void addLast(E e){
        if (data.length == size){
            //扩容
            resize(data.length * 2);
        }
        data[size] = e;
        size++;
    }

    //在 index 索引的位置添加一个元素 element
    public void add(int index,E element){
        checkPositionIndex(index);
        //data[index..] -> data[index+1..]
        //1.数据搬移
        System.arraycopy(data,index,data,index+1,size-index);
        //2.赋值
        data[index] = element;
        size++;
    }


    /***** 删 *****/

    //删除数组的最后一个元素并返回
    public E removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        if (size < data.length / 4){
            resize(data.length / 2);
        }
        E deletedVal = data[size - 1];

        data[size - 1] = null;
        size--;

        return deletedVal;
    }

    //删除 index 对应的元素并返回
    public E remove(int index){
        checkElementIndex(index);

        if (size < data.length / 4){
            resize(data.length / 2);
        }

        E deletedVal = data[index];

        //1.数据搬移
//        data[index+1..] -> data[index..]
        System.arraycopy(data,index+1,data,index,size-index-1);
        //2.删除最后一个数据
        data[size - 1] = null;
        size--;
        return deletedVal;
    }


    /***** 查 *****/

    //返回索引 index 对应的元素
    public E get(int index){
        checkElementIndex(index);
        return data[index];
    }


    /***** 查 *****/

    //将索引 index 的元素改为 element 并返回之前的元素
    public E set(int index,E element){
        checkElementIndex(index);
        E oldVal = data[index];
        data[index] = element;
        return oldVal;
    }

    //工具函数
    public int size(){return  size;}

    public boolean isEmpty(){return  size == 0;}

    //私有函数
    private boolean isElementIndex(int index){return index >= 0 && index < size;}

    private boolean isPositionIndex(int index){return index >= 0 && index <= size;}

    //检查 index 索引位置是否可以存在元素
    private void checkElementIndex(int index){
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index" + index +",Size:"+size);
    }

    //检查 index 索引位置是否可以添加元素
    private void checkPositionIndex(int index){
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index" + index +",Size:"+size);
    }

    //将数组大小扩容或缩小为 newCap
    private void resize(int newCap){
        E[] temp= (E[]) new Object[newCap];
        //copy数据
        //temp[0..0+size] = data[0..0+size]
        System.arraycopy(data,0,temp,0,size);
        data = temp;
    }
}
