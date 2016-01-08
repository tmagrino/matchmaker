package model;

/**
 * 
 * Represents a profile photo for a {@link Researcher}
 * <p>
 * Modified version of bovinemagnet's code
 * https://github.com/bovinemagnet/JPAandEJB-JPAImageExample/tree/master/src/main/java/au/com/sup/jpatest
 * <p>
 * Not in use
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import javax.persistence.*;

import org.eclipse.jdt.internal.compiler.batch.Main;

//import com.sun.istack.internal.logging.Logger;

@Entity
public class ProfileImage {

	// 1MB
	public static final int MAX_SIZE = 1048576;

	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content", length = MAX_SIZE, nullable = false)
	private byte[] content;

	@OneToOne (mappedBy = "photo")
	private Researcher researcher;

	@Version
	long version = 0;
	
	public ProfileImage() {

	}

	@SuppressWarnings("resource")
	ProfileImage(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		byte[] content = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < content.length && (numRead = is.read(content, offset, content.length - offset)) >= 0)
		{
			offset += numRead;
		}
		// Ensure all the bytes have been read in
		if (offset < content.length)
		{
			throw new IOException("Could not completely read file " + file.getName());
		}
		// Close the input stream and return bytes
		System.out.println("New image created: "+content.length+" bytes");
		is.close();
	}

	public static int getMaxSize() {
		return MAX_SIZE;
	}

	public long getId() {
		return id;
	}

	public byte[] getContent() {
		return content;
	}

	public Researcher getResearcher() {
		return researcher;
	}

	void setResearcher(Researcher researcher) {
		this.researcher = researcher;
		if (researcher.getPhoto() != this) {
			researcher.setPhoto(this);
		}
	}
	
	
}
