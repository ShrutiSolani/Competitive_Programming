/*package whatever //do not write package name here */

// driver code

import java.util.*;
import java.io.*;
import java.lang.*;

class Node
{
    int data;
    Node next;
}

class DetectLoopHeadInLL
{
    public static Node newNode(int data){
        Node temp = new Node();
        temp.data = data;
        temp.next = null;
        return temp;
    }
    
    public static void makeLoop(Node head, int x){
        if (x == 0)
            return;
        Node curr = head;
        Node last = head;

        int currentPosition = 1;
        while (currentPosition < x)
        {
            curr = curr.next;
            currentPosition++;
        }
        
        while (last.next != null)
            last = last.next;
        last.next = curr;
    }
    
    public static boolean detectLoop(Node head){
        Node hare = head.next;
        Node tortoise = head;
        while( hare != tortoise )
        {
            if(hare==null || hare.next==null) return false;
            hare = hare.next.next;
            tortoise = tortoise.next;
        }
        return true;
    }
    
    public static int length(Node head){
        int ret=0;
        while(head!=null)
        {
            ret += 1;
            head = head.next;
        }
        return ret;
    }
    
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t--> 0)
        {
            int n = sc.nextInt();
            
            int num = sc.nextInt();
            Node head = newNode(num);
            Node tail = head;
            
            for(int i=0; i<n-1; i++)
            {
                num = sc.nextInt();
                tail.next = newNode(num);
                tail = tail.next;
            }
            
            int pos = sc.nextInt();
            makeLoop(head, pos);
            
            Solution x = new Solution();
            Node loophead = x.findFirstLoopNode(head);
            if(loophead != null){
                System.out.println(loophead.data);
            }
            else{
                System.out.println("NO LOOP");
            }
        }
    }
}
// } Driver Code Ends


/*
class Node
{
    int data;
    Node next;
}
*/

class Solution
{
    //Function to remove a loop in the linked list.
    public static Node findFirstLoopNode(Node head){
        // code here
        // remove the loop without losing any nodes
         Boolean detect = DetectLoopHeadInLL.detectLoop(head);
        // System.out.println(detect);
        if(detect == true){
            Node hare = head.next;
            Node tortoise = head;
            while(hare != tortoise){
                hare = hare.next.next;
                tortoise = tortoise.next;
            }
            Node loopnode1 = tortoise;
            Node loopnode2 = tortoise;
            int k = 1;
            while(loopnode1.next != loopnode2){
                loopnode1 = loopnode1.next;
                k++;
            }
            loopnode1 = loopnode2 = head;
            for(int i = 0; i < k; i++){
                loopnode2 = loopnode2.next;
            }
            while(loopnode2 != loopnode1){
                loopnode1 = loopnode1.next;
                loopnode2 = loopnode2.next;
            }
            return loopnode2;
        }
        else{
            return null;
        }
    }
}