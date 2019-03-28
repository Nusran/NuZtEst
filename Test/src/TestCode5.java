
import java.io.File;
import java.io.IOException;





public class TestCode5 {

	public static void main(String[] args) throws Throwable{
		   String fileSeparator = System.getProperty("file.separator");
	        
	        //absolute file name with path
	        String absoluteFilePath = "C:\\Users\\nusrans\\Desktop\\tt.txt";
	        File file = new File(absoluteFilePath);
			file.createNewFile();
			String str = "\n";
			try {
				for(int i=0; i<5 ; i++)
			    java.nio.file.Files.write(java.nio.file.Paths.get(absoluteFilePath), (String.valueOf(1)+" | ").getBytes(), java.nio.file.StandardOpenOption.APPEND);
				 java.nio.file.Files.write(java.nio.file.Paths.get(absoluteFilePath), " : ".getBytes(), java.nio.file.StandardOpenOption.APPEND);
				 java.nio.file.Files.write(java.nio.file.Paths.get(absoluteFilePath), String.valueOf(0).getBytes(), java.nio.file.StandardOpenOption.APPEND);
				 java.nio.file.Files.write(java.nio.file.Paths.get(absoluteFilePath), System.lineSeparator().getBytes(), java.nio.file.StandardOpenOption.APPEND);
				
			} catch (IOException e) {
				System.out.println("Problem occurs when deleting the directory : " + absoluteFilePath);
				e.printStackTrace();
			}

	}

}
