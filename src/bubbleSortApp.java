import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class bubbleSortApp{
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter array length: ");  // input length of array
        int arrayLength = scanner.nextInt();
        int[] randomArray = createRandomArray(arrayLength);
        
        System.out.print("Enter filename: ");  // input filename
        String filename = scanner.next();
        writeArrayToFile(randomArray, filename);
        
        int[] arrayFromFile = readFileToArray(filename);  // calls readfileToArray method to store integers in arrayFromFile
        
        System.out.println("Array before sorting: " + Arrays.toString(arrayFromFile));
        bubbleSort(arrayFromFile);
        System.out.println("Array after sorting: " + Arrays.toString(arrayFromFile));

        
        writeArrayToFile(arrayFromFile, "sorted_array.txt"); // saves sorted array to array_sorted.txt
        System.out.println("Sorted array saved to sorted_array.txt");
        
        scanner.close();
    }

    public static int[] createRandomArray(int arrayLength){      // creates array of random ints 1-100
        int[] randomArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = (int) (Math.random() * 100) + 1; 
        }
        System.out.println(Arrays.toString(randomArray));
        return randomArray;
    }

    public static void writeArrayToFile(int[] array, String filename) {   // writes each int on a new line in a file
        try (FileWriter writer = new FileWriter(filename)) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] readFileToArray(String filename) {      // reads integers from file and returns them as array
        ArrayList<Integer> arrayList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextInt()) {
                arrayList.add(scanner.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void bubbleSort(int[] array) {   // bubble sort
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
               
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
