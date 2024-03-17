public class Main {
        private T[] array; // Genel veri dizisi
    private int size; // Dizideki eleman sayısı
    private static final int DEFAULT_CAPACITY = 10; // Varsayılan başlangıç kapasitesi

    // Boş constructor
    public MyList() {
        this(DEFAULT_CAPACITY); // Varsayılan kapasiteyi kullanarak diğer constructor'ı çağırır
    }

    // Kapasite belirtilmiş constructor
    public MyList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Kapasite 0'dan büyük olmalıdır");
        }
        array = (T[]) new Object[capacity]; // Belirtilen kapasiteye göre dizi oluşturulur
        size = 0; // Eleman sayısı başlangıçta 0 olarak ayarlanır
    }

    // Dizideki eleman sayısını döndüren metod
    public int size() {
        return size;
    }

    // Dizinin kapasitesini döndüren metod
    public int getCapacity() {
        return array.length;
    }

    // Eleman eklemek için kullanılan metod
    public void add(T data) {
        if (size == array.length) {
            ensureCapacity(); // Dizi dolu ise kapasite arttırılır
        }
        array[size++] = data; // Yeni eleman dizinin sonuna eklenir
    }

    // Dizi boyutunu arttıran özel metod
    private void ensureCapacity() {
        int newCapacity = array.length * 2; // Yeni kapasite, mevcut kapasitenin 2 katı olacak şekilde hesaplanır
        T[] newArray = (T[]) new Object[newCapacity]; // Yeni bir dizi oluşturulur
        System.arraycopy(array, 0, newArray, 0, size); // Eski dizideki elemanlar yeni diziye kopyalanır
        array = newArray; // Yeni dizi eski diziye atanır
    }

    // Belirtilen indisteki elemanı döndüren metod
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null; // Geçersiz indis, null döndürülür
        }
        return array[index];
    }

    // Belirtilen indisteki elemanı silen metod
    public T remove(int index) {
        if (index < 0 || index >= size) {
            return null; // Geçersiz indis, null döndürülür
        }
        T removedElement = array[index]; // Silinecek eleman geçici bir değişkene atanır
        // Silinecek elemandan sonraki elemanlar sola kaydırılır
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null; // Dizinin son elemanı null yapılır ve eleman sayısı azaltılır
        return removedElement;
    }

    // Belirtilen indisteki elemanı yenisi ile değiştiren metod
    public T set(int index, T data) {
        if (index < 0 || index >= size) {
            return null; // Geçersiz indis, null döndürülür
        }
        T replacedElement = array[index]; // Değiştirilecek eleman geçici bir değişkene atanır
        array[index] = data; // Yeni eleman belirtilen indise yerleştirilir
        return replacedElement;
    }

    // Dizideki elemanları listelemek için kullanılan metod
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(array[i]);
            if (i != size - 1) {
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }

    // Parametrede verilen nesnenin indeksini döndüren metod
    public int indexOf(T data) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(data)) {
                return i;
            }
        }
        return -1; // Nesne bulunamadıysa -1 döndürülür
    }

    // Belirtilen öğenin listedeki son indeksini döndüren metod
    public int lastIndexOf(T data) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(data)) {
                return i;
            }
        }
        return -1; // Nesne bulunamadıysa -1 döndürülür
    }

    // Listenin boş olup olmadığını kontrol eden metod
    public boolean isEmpty() {
        return size == 0;
    }

    // Listedeki öğeleri dizi olarak döndüren metod
    public T[] toArray() {
        T[] result = (T[]) new Object[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }

    // Listedeki belirtilen indeks aralığına ait alt bir liste döndüren metod
    public MyList<T> subList(int start, int finish) {
        if (start < 0 || finish >= size || start > finish) {
            throw new IllegalArgumentException("Geçersiz başlangıç veya bitiş indeksi");
        }
        MyList<T> sublist = new MyList<>(finish - start + 1);
        for (int i = start; i <= finish; i++) {
            sublist.add(array[i]);
        }
        return sublist;
    }

    // Parametrede verilen değerin listede olup olmadığını kontrol eden metod
    public boolean contains(T data) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(data)) {
                return true;
            }
        }
        return false;
    }

    // Listedeki bütün öğeleri silen metod
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }
}
