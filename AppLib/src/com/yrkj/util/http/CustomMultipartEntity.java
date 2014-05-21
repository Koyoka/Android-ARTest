package com.yrkj.util.http;


import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;

public class CustomMultipartEntity extends MultipartEntity {

	private final HttpProgressListener listener;

	public CustomMultipartEntity(final HttpProgressListener listener) {
		super();
		this.listener = listener;
		
	}

	public CustomMultipartEntity(final HttpMultipartMode mode,
			final HttpProgressListener listener) {
		super(mode);
		this.listener = listener;
	}

	public CustomMultipartEntity(HttpMultipartMode mode, final String boundary,
			final Charset charset, final HttpProgressListener listener) {
		super(mode, boundary, charset);
		this.listener = listener;
	}

	@Override
	public void writeTo(OutputStream outstream) throws IOException {
		super.writeTo(new CountingOutputStream(outstream, this.listener,this.getContentLength()));
	}

	public static interface HttpProgressListener {
		void transferred(long num,long contentLenth);
	}

	public static class CountingOutputStream extends FilterOutputStream {
		
		private final HttpProgressListener listener;
		private long transferred;
		private long contentLenth;

		public CountingOutputStream(final OutputStream out,
				final HttpProgressListener listener,long lenth) {
			super(out);
			this.contentLenth = lenth;
			this.listener = listener;
			this.transferred = 0;
		}

		public void write(byte[] b, int off, int len) throws IOException {
			out.write(b, off, len);
			this.transferred += len;
			this.listener.transferred(this.transferred,this.contentLenth);
		}

		public void write(int b) throws IOException {
			out.write(b);
			this.transferred++;
			this.listener.transferred(this.transferred,this.contentLenth);
		}
	}

}
