package net.dongliu.requests.body;

import net.dongliu.requests.Parameter;
import net.dongliu.requests.utils.Lists;
import net.dongliu.requests.utils.URIEncoder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

import static net.dongliu.requests.HttpHeaders.CONTENT_TYPE_FORM_ENCODED;

/**
 * @author Liu Dong
 */
class FormRequestBody extends RequestBody<Collection<? extends Map.Entry<String, ?>>> {
    FormRequestBody(Collection<? extends Map.Entry<String, ?>> body) {
        super(body, CONTENT_TYPE_FORM_ENCODED, true);
    }

    @Override
    public void writeBody(OutputStream os, Charset charset) throws IOException {
        String content = URIEncoder.encodeForms(
                Lists.map(getBody(), p -> Parameter.of(p.getKey(), String.valueOf(p.getValue()))),
                charset);
        try (Writer writer = new OutputStreamWriter(os, charset)) {
            writer.write(content);
        }
    }
}
