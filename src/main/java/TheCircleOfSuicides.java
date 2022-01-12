import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author malchi
 */
public class TheCircleOfSuicides {

    
    /*
    this function put 1 in the array
    */
    public static void reset(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
    }
    
    /*
    this function check where there is a place in alive
    */
    public static int next(int[] arr, int x) {
        for (int i = x; i < arr.length; i++) {
            if (arr[i] == 1) {
                return i;
            }
        }
        for (int i = 0; i <= x; i++) {
            if (arr[i] == 1) {
                return i;
            }
        }
        return x;
    }

    /*
    this function check which of the place stay in alive
    */
    public static int array() {
        int arr[], num, j, i;
        System.out.println("enter num of people: ");
        num = scan.nextInt();
        arr = new int[num];
        reset(arr);
        i = 0;
        j = next(arr, i + 1);
        while (i != j) {
            if (arr[i] == 1) {
                arr[j] = 0;
                i = next(arr, i + 1);
                j = next(arr, i + 1);
            }

        }
        return i + 1;
    }

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println(array());
        //123
    }

}
