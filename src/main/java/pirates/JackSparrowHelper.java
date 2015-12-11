package pirates;

public interface JackSparrowHelper {
    /**
     * param path            Полный путь к csv файлу с ценами и количествами (sources.csv), доступными в разных местах
     * param numberOfGallons совокупное количество галлонов, нужное Джеку
     * return <tt>Purchases</tt> детальный совет Джеку, где и в каком количестве покупать ром
     */
    Purchases helpJackSparrow(String path, int numberOfGallons);
}
