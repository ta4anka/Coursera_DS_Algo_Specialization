package module4_divide_and_conquer;

import java.util.Arrays;
import java.util.Comparator;

public class MultilingualDictionary {
    private final String[] en, ru, uk;         // Оригинальные массивы слов
    private final String[] sortedEn, sortedRu, sortedUk; // Отсортированные массивы для бинарного поиска
    private final int[] enIndices, ruIndices, ukIndices; // Индексы для связи с оригинальными массивами

    public MultilingualDictionary(String[] englishWords, String[] russianWords, String[] ukrainianWords) {
        int size = englishWords.length;

        // Инициализация оригинальных массивов
        en = englishWords;
        ru = russianWords;
        uk = ukrainianWords;

        // Создание копий для сортировки и массивов индексов
        sortedEn = Arrays.copyOf(en, size);
        sortedRu = Arrays.copyOf(ru, size);
        sortedUk = Arrays.copyOf(uk, size);

        enIndices = new int[size];
        ruIndices = new int[size];
        ukIndices = new int[size];

        // Инициализация массивов индексов
        for (int i = 0; i < size; i++) {
            enIndices[i] = i;
            ruIndices[i] = i;
            ukIndices[i] = i;
        }

        // Сортировка массивов с соответствующими индексами
        sortWithIndices(sortedEn, enIndices);
        sortWithIndices(sortedRu, ruIndices);
        sortWithIndices(sortedUk, ukIndices);
    }

    // Метод для сортировки массива и соответствующих индексов
    private void sortWithIndices(String[] words, int[] indices) {
        Integer[] tempIndices = new Integer[words.length];
        for (int i = 0; i < words.length; i++) tempIndices[i] = indices[i];

        Arrays.sort(tempIndices, Comparator.comparing(i -> words[i]));

        for (int i = 0; i < words.length; i++) {
            indices[i] = tempIndices[i];
            words[i] = words[indices[i]];
        }
    }

    // Метод для нахождения перевода слова
    public String findTranslation(String word, String sourceLanguage, String targetLanguage) {
        int index = binarySearch(word, sourceLanguage); // Ищем индекс в отсортированном массиве
        if (index == -1) return null; // Слово не найдено

        int originalIndex = getOriginalIndex(sourceLanguage, index);
        return getTranslationByIndex(targetLanguage, originalIndex);
    }

    // Метод бинарного поиска по отсортированному массиву
    private int binarySearch(String word, String language) {
        String[] sortedArray = getSortedArray(language);
        int left = 0, right = sortedArray.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = sortedArray[mid].compareTo(word);

            if (comparison == 0) return mid; // Нашли
            else if (comparison < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1; // Не найдено
    }

    // Получение отсортированного массива по языку
    private String[] getSortedArray(String language) {
        return switch (language.toLowerCase()) {
            case "english" -> sortedEn;
            case "russian" -> sortedRu;
            case "ukrainian" -> sortedUk;
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        };
    }

    // Получение оригинального индекса в несортированном массиве
    private int getOriginalIndex(String language, int sortedIndex) {
        return switch (language.toLowerCase()) {
            case "english" -> enIndices[sortedIndex];
            case "russian" -> ruIndices[sortedIndex];
            case "ukrainian" -> ukIndices[sortedIndex];
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        };
    }

    // Получение перевода по оригинальному индексу
    private String getTranslationByIndex(String language, int index) {
        return switch (language.toLowerCase()) {
            case "english" -> en[index];
            case "russian" -> ru[index];
            case "ukrainian" -> uk[index];
            default -> throw new IllegalArgumentException("Unsupported target language: " + language);
        };
    }

    // Пример использования
    public static void main(String[] args) {
        // Инициализация словаря 100000 слов для каждого языка
        String[] englishWords = {"hello", "world", "cat", "dog"};  // Упрощенный пример, здесь должно быть 100000 слов
        String[] russianWords = {"привет", "мир", "кот", "собака"};
        String[] ukrainianWords = {"привіт", "світ", "кіт", "пес"};

        MultilingualDictionary dictionary = new MultilingualDictionary(englishWords, russianWords, ukrainianWords);

        String translation = dictionary.findTranslation("cat", "english", "russian");
        System.out.println("Translation: " + translation); // Ожидается "кот"
    }
}
