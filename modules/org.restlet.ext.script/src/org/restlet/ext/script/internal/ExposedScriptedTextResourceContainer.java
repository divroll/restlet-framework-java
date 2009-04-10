/**
 * Copyright 2005-2009 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.ext.script.internal;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.ConcurrentMap;

import javax.script.ScriptException;

import org.restlet.data.CharacterSet;
import org.restlet.data.Language;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.ext.script.ScriptedTextResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.representation.Variant;

import com.threecrickets.scripturian.CompositeScript;
import com.threecrickets.scripturian.CompositeScriptContext;
import com.threecrickets.scripturian.ScriptSource;

/**
 * This is the type of the <code>script.container</code> variable exposed to the
 * script.
 * 
 * @author Tal Liron
 */
public class ExposedScriptedTextResourceContainer {
    /**
     * The resource.
     */
    private final ScriptedTextResource resource;

    /**
     * Flag to signify that we should enter streaming mode.
     */
    private boolean startStreaming;

    /**
     * The {@link Variant} of this request.
     */
    private final Variant variant;

    /**
     * Cache for caching mode.
     */
    private final ConcurrentMap<String, RepresentableString> cache;

    /**
     * Whether to flush the writers after every line in streaming mode.
     */
    private boolean flushLines;

    /**
     * The {@link MediaType} that will be used for the generated string.
     */
    private MediaType mediaType;

    /**
     * The {@link CharacterSet} that will be used for the generated string.
     */
    private CharacterSet characterSet;

    /**
     * The {@link Language} that will be used for the generated string.
     */
    private Language language;

    /**
     * This boolean is true when the writer is in streaming mode.
     */
    protected boolean isStreaming;

    /**
     * Buffer used for caching mode.
     */
    private StringBuffer buffer;

    /**
     * The composite script context.
     */
    private final CompositeScriptContext compositeScriptContext;

    /**
     * Constructs a container with media type and character set according to the
     * variant, or {@link ScriptedTextResource#getDefaultCharacterSet()} if none
     * is provided.
     * 
     * @param resource
     *            The resource
     * @param variant
     *            The variant
     * @param cache
     *            The cache (used for caching mode)
     */
    public ExposedScriptedTextResourceContainer(ScriptedTextResource resource,
            Variant variant, ConcurrentMap<String, RepresentableString> cache) {
        this.resource = resource;
        this.variant = variant;
        this.cache = cache;
        this.mediaType = variant.getMediaType();
        this.characterSet = variant.getCharacterSet();
        if (this.characterSet == null) {
            this.characterSet = resource.getDefaultCharacterSet();
        }
        this.compositeScriptContext = new CompositeScriptContext(resource
                .getScriptEngineManager());
    }

    /**
     * The {@link CharacterSet} that will be used for the generated string.
     * Defaults to what the client requested (in container.variant), or to the
     * value of {@link ScriptedTextResource#defaultCharacterSet} if the client
     * did not specify it. If not in streaming mode, your script can change this
     * to something else.
     * 
     * @return The character set
     * @see #setCharacterSet(CharacterSet)
     */
    public CharacterSet getCharacterSet() {
        return this.characterSet;
    }

    /**
     * Identical to {@link #isStreaming()}. Supports scripting engines which
     * don't know how to recognize the "is" getter notation, but can recognize
     * the "get" notation.
     * 
     * @return True if in streaming mode, false if in caching mode
     * @see #isStreaming()
     */
    public boolean getIsStreaming() {
        return isStreaming();
    }

    /**
     * The {@link Language} that will be used for the generated string. Defaults
     * to null. If not in streaming mode, your script can change this to
     * something else.
     * 
     * @return The language or null if set
     * @see #setLanguage(Language)
     */
    public Language getLanguage() {
        return this.language;
    }

    /**
     * The {@link MediaType} that will be used for the generated string.
     * Defaults to what the client requested (in container.variant). If not in
     * streaming mode, your script can change this to something else.
     * 
     * @return The media type
     * @see #setMediaType(MediaType)
     */
    public MediaType getMediaType() {
        return this.mediaType;
    }

    /**
     * The {@link Request}. Useful for accessing URL attributes, form
     * parameters, etc.
     * 
     * @return The request
     */
    public Request getRequest() {
        return this.resource.getRequest();
    }

    /**
     * The {@link Response}. Useful for explicitly setting response
     * characteristics.
     * 
     * @return The response
     */
    public Response getResponse() {
        return this.resource.getResponse();
    }

    /**
     * The {@link ScriptSource} used to fetch and cache scripts.
     * 
     * @return The script source
     */
    public ScriptSource<CompositeScript> getSource() {
        return this.resource.getScriptSource();
    }

    /**
     * The {@link Variant} of this request. Useful for interrogating the
     * client's preferences.
     * 
     * @return The variant
     */
    public Variant getVariant() {
        return this.variant;
    }

    /**
     * This powerful method allows scripts to execute other scripts in place,
     * and is useful for creating large, maintainable applications based on
     * scripts. Included scripts can act as a library or toolkit and can even be
     * shared among many applications. The included script does not have to be
     * in the same language or use the same engine as the calling script.
     * However, if they do use the same engine, then methods, functions,
     * modules, etc., could be shared. It is important to note that how this
     * works varies a lot per scripting platform. For example, in JRuby, every
     * script is run in its own scope, so that sharing would have to be done
     * explicitly in the global scope. See the included Ruby composite script
     * example for a discussion of various ways to do this.
     * 
     * @param name
     *            The script name
     * @return A representation of the script's output
     * @throws IOException
     * @throws ScriptException
     */
    public Representation include(String name) throws IOException,
            ScriptException {
        return include(name, null);
    }

    /**
     * As {@link #include(String)}, except that the script is not composite. As
     * such, you must explicitly specify the name of the scripting engine that
     * should evaluate it.
     * 
     * @param name
     *            The script name
     * @param scriptEngineName
     *            The script engine name (if null, behaves identically to
     *            {@link #include(String)}
     * @return A representation of the script's output
     * @throws IOException
     * @throws ScriptException
     */
    public Representation include(String name, String scriptEngineName)
            throws IOException, ScriptException {
        boolean isStreaming = isStreaming();
        Writer writer = this.resource.getWriter();

        // Get script descriptor
        ScriptSource.ScriptDescriptor<CompositeScript> scriptDescriptor = this.resource
                .getScriptSource().getScriptDescriptor(name);

        CompositeScript script = scriptDescriptor.getScript();
        if (script == null) {
            // Create script from descriptor
            String text = scriptDescriptor.getText();
            if (scriptEngineName != null) {
                text = CompositeScript.DEFAULT_DELIMITER1_START
                        + scriptEngineName + " " + text
                        + CompositeScript.DEFAULT_DELIMITER1_END;
            }
            script = new CompositeScript(text, this.resource
                    .getScriptEngineManager(), this.resource
                    .getDefaultScriptEngineName(), this.resource
                    .getScriptSource(), this.resource.isAllowCompilation());
            CompositeScript existing = scriptDescriptor
                    .setScriptIfAbsent(script);
            if (existing != null) {
                script = existing;
            }
        }

        // Special handling for trivial scripts
        String trivial = script.getTrivial();
        if (trivial != null) {
            if (writer != null) {
                writer.write(trivial);
            }
            return new StringRepresentation(trivial, getMediaType(),
                    getLanguage(), getCharacterSet());
        }

        int startPosition = 0;

        // Make sure we have a valid writer for caching mode
        if (!isStreaming) {
            if (writer == null) {
                StringWriter stringWriter = new StringWriter();
                this.buffer = stringWriter.getBuffer();
                writer = new BufferedWriter(stringWriter);
                this.resource.setWriter(writer);
            } else {
                writer.flush();
                startPosition = this.buffer.length();
            }
        }

        try {
            // Do not allow caching in streaming mode
            if (script.run(!isStreaming, writer,
                    this.resource.getErrorWriter(), false,
                    this.compositeScriptContext, this, this.resource
                            .getScriptContextController())) {

                // Did the script ask us to start streaming?
                if (this.startStreaming) {
                    this.startStreaming = false;

                    // Note that this will cause the script to run again!
                    return new ScriptedTextStreamingRepresentation(
                            this.resource, this, this.compositeScriptContext,
                            this.resource.getScriptContextController(), script,
                            this.flushLines);
                }

                if (isStreaming) {
                    // Nothing to return in streaming mode
                    return null;
                } else {
                    writer.flush();

                    // Get the buffer from when we ran the script
                    RepresentableString string = new RepresentableString(
                            this.buffer.substring(startPosition),
                            getMediaType(), getLanguage(), getCharacterSet());

                    // Cache it
                    this.cache.put(name, string);

                    // Return a representation of the entire buffer
                    if (startPosition == 0) {
                        return string.represent();
                    } else {
                        return new StringRepresentation(this.buffer.toString(),
                                getMediaType(), getLanguage(),
                                getCharacterSet());
                    }
                }
            } else {
                // Attempt to use cache
                RepresentableString string = this.cache.get(name);
                if (string != null) {
                    if (writer != null) {
                        writer.write(string.getString());
                    }
                    return string.represent();
                } else {
                    return null;
                }
            }
        } catch (ScriptException x) {
            // Did the script ask us to start streaming?
            if (this.startStreaming) {
                this.startStreaming = false;

                // Note that this will cause the script to run again!
                return new ScriptedTextStreamingRepresentation(this.resource,
                        this, this.compositeScriptContext, this.resource
                                .getScriptContextController(), script,
                        this.flushLines);

                // Note that we will allow exceptions in scripts that ask us
                // to start streaming! In fact, throwing an exception is a
                // good way for the script to signal that it's done and is
                // ready to start streaming.
            } else {
                throw x;
            }
        }
    }

    /**
     * This boolean is true when the writer is in streaming mode.
     * 
     * @return True if in streaming mode, false if in caching mode
     */
    public boolean isStreaming() {
        return this.isStreaming;
    }

    /**
     * Throws an {@link IllegalStateException} if in streaming mode.
     * 
     * @param characterSet
     *            The character set
     * @see #getCharacterSet()
     */
    public void setCharacterSet(CharacterSet characterSet) {
        if (isStreaming()) {
            throw new IllegalStateException(
                    "Cannot change character set while streaming");
        }
        this.characterSet = characterSet;
    }

    /**
     * Throws an {@link IllegalStateException} if in streaming mode.
     * 
     * @param language
     *            The language or null
     * @see #getLanguage()
     */
    public void setLanguage(Language language) {
        if (isStreaming()) {
            throw new IllegalStateException(
                    "Cannot change language while streaming");
        }
        this.language = language;
    }

    /**
     * Throws an {@link IllegalStateException} if in streaming mode.
     * 
     * @param mediaType
     *            The media type
     * @see #getMediaType()
     */
    public void setMediaType(MediaType mediaType) {
        if (isStreaming()) {
            throw new IllegalStateException(
                    "Cannot change media type while streaming");
        }
        this.mediaType = mediaType;
    }

    /**
     * If you are in caching mode, calling this method will return true and
     * cause the script to run again, where this next run will be in streaming
     * mode. Whatever output the script created in the current run is discarded,
     * and all further exceptions are ignored. For this reason, it's probably
     * best to call container.stream() as early as possible in the script, and
     * then to quit the script as soon as possible if it returns true. For
     * example, your script can start by testing whether it will have a lot of
     * output, and if so, set output characteristics, call container.stream(),
     * and quit. If you are already in streaming mode, calling this method has
     * no effect and returns false. Note that a good way to quit the script is
     * to throw an exception, because it will end the script and otherwise be
     * ignored.
     * <p>
     * By default, writers will be automatically flushed after every line in
     * streaming mode. If you want to disable this behavior, use
     * {@link #stream(boolean)}.
     * 
     * @return True if started streaming mode, false if already in streaming
     *         mode
     * @see #stream(boolean)
     */
    public boolean stream() {
        return stream(true);
    }

    /**
     * If you are in caching mode, calling this method will return true and
     * cause the script to run again, where this next run will be in streaming
     * mode. Whatever output the script created in the current run is discarded,
     * and all further exceptions are ignored. For this reason, it's probably
     * best to call container.stream() as early as possible in the script, and
     * then to quit the script as soon as possible if it returns true. For
     * example, your script can start by testing whether it will have a lot of
     * output, and if so, set output characteristics, call container.stream(),
     * and quit. If you are already in streaming mode, calling this method has
     * no effect and returns false. Note that a good way to quit the script is
     * to throw an exception, because it will end the script and otherwise be
     * ignored.
     * 
     * @param flushLines
     *            Whether to flush the writers after every line in streaming
     *            mode
     * @return True if started streaming mode, false if already in streaming
     *         mode
     * @see #stream()
     */
    public boolean stream(boolean flushLines) {
        if (isStreaming()) {
            return false;
        }
        this.startStreaming = true;
        this.flushLines = flushLines;
        return true;
    }
}