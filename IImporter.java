package infra.importer;

public interface IImporter<T> {
	public T importFromCsv(String folderPath, String fileName) throws Exception;
}
