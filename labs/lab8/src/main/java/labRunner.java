public class labRunner {

    /**
     * This method simply swaps 2 elements in an array.
     * This is used for both insertion sort and quicksort
     *
     * @param arr array that contains the elements that need swapped
     * @param i   index of the first element
     * @param j   index of the second element
     */
    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];

        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * INSERTION SORT
     *
     * do NOT use Collections.sort that will result in a 0 for this method
     *
     * This is an insertion sort where you must sort the elements in the array
     * For an in depth explanation of insertion sort see:
     *
     *
     * @param arr array to be sorted
     * @return the sorted array
     */
    public int[] insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int j = i;
            int curr = arr[j];

            while(j > 0 && arr[j - 1] > curr) {
                swap(arr, j - 1, j);
                j -= 1;
            }
        }
        return arr;
    }

    /**
     * QUICKSORT
     *
     * do NOT use Collections.sort that will result in a 0 for this method
     *
     * This is a recursive quicksort that will partition the array into 2 spots
     * if the starting index is less that the https://www.geeksforgeeks.org/insertion-sort/ending index and recursively call
     * quick sort on those 2 sections
     *
     * @param arr  array to be sorted
     * @param low  starting index
     * @param high ending index
     */
    public void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int partIndex = partition(arr, low, high);
            quickSort(arr, low, partIndex - 1);
            quickSort(arr, partIndex + 1, high);
        }
    }

    /**
     * PARTITION
     *
     * This method sets the last element as the pivot element and
     * places is where it should go in the sorted array by placing
     * all elements smaller than the pivot to the left of the pivot
     * and all elements greater than the pivot to the right of the pivot
     *
     * @param arr  array you are finding the index of
     * @param low  starting index
     * @param high ending index
     * @return the correct index where you should partition the array
     */
    public int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for(int j = low; j <= high -1; j++) {
            if(arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, high);

        return i + 1;
    }
}
