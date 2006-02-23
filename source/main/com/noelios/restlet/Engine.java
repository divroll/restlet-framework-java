/*
 * Copyright 2005-2006 J�r�me LOUVEL
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the "License").  You may not use this file except
 * in compliance with the License.
 *
 * You can obtain a copy of the license at
 * http://www.opensource.org/licenses/cddl1.txt
 * See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * HEADER in each file and include the License file at
 * http://www.opensource.org/licenses/cddl1.txt
 * If applicable, add the following below this CDDL
 * HEADER, with the fields enclosed by brackets "[]"
 * replaced with your own identifying information:
 * Portions Copyright [yyyy] [name of copyright owner]
 */

package com.noelios.restlet;

import org.restlet.Chainlet;
import org.restlet.Factory;
import org.restlet.Manager;
import org.restlet.Maplet;
import org.restlet.UniformCall;
import org.restlet.UniformInterface;
import org.restlet.component.RestletContainer;
import org.restlet.component.RestletServer;
import org.restlet.connector.Client;
import org.restlet.connector.Server;
import org.restlet.data.CharacterSet;
import org.restlet.data.Cookie;
import org.restlet.data.CookieSetting;
import org.restlet.data.Encoding;
import org.restlet.data.Form;
import org.restlet.data.Language;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Parameter;
import org.restlet.data.Protocol;
import org.restlet.data.Protocols;
import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.data.Tag;

import com.noelios.restlet.component.RestletContainerImpl;
import com.noelios.restlet.component.RestletServerImpl;
import com.noelios.restlet.connector.HttpClientImpl;
import com.noelios.restlet.data.CharacterSetImpl;
import com.noelios.restlet.data.CookieImpl;
import com.noelios.restlet.data.CookieSettingImpl;
import com.noelios.restlet.data.EncodingImpl;
import com.noelios.restlet.data.FormImpl;
import com.noelios.restlet.data.LanguageImpl;
import com.noelios.restlet.data.MediaTypeImpl;
import com.noelios.restlet.data.MethodImpl;
import com.noelios.restlet.data.ParameterImpl;
import com.noelios.restlet.data.ReferenceImpl;
import com.noelios.restlet.data.StatusImpl;
import com.noelios.restlet.data.TagImpl;
import com.noelios.restlet.ext.javamail.JavaMailClient;
import com.noelios.restlet.ext.jetty.JettyServer;

/**
 * Noelios Restlet Engine.<br/>
 * Also acts as a factory implementation.
 */
public class Engine implements Factory
{
   public static final String VERSION_LONG = "1.0 beta 4";
   public static final String VERSION_SHORT = "1.0b4";
   public static final String VERSION_HEADER = "Noelios-Restlet-Engine/" + VERSION_SHORT;
   
   /**
    * Registers the Noelios Restlet Engine
    */
   public static void register()
   {
      Manager.registerFactory(new Engine());
   }

   /**
    * Create a new client connector for a given protocol.
    * @param protocol The connector protocol.
    * @param name The unique connector name.
    * @return The new client connector.
    */
   public Client createClient(Protocol protocol, String name)
   {
      Client result = null;
      
      if(Protocols.HTTP.equals(protocol)) 
      {
         result = new HttpClientImpl(name);
      }
      else if(Protocols.HTTPS.equals(protocol)) 
      {
         result = new HttpClientImpl(name);
      }
      else if(Protocols.SMTP.equals(protocol)) 
      {
         result = new JavaMailClient(name);
      }
      
      return result;
   }

   /**
    * Create a new server connector for a given protocol.
    * @param protocol The connector protocol.
    * @param name The unique connector name.
    * @param target The target handler.
    * @param address The optional listening IP address (local host used if null).
    * @param port The listening port.
    * @return The new server connector.
    */
   public Server createServer(Protocol protocol, String name, UniformInterface target, String address, int port)
   {
      Server result = null;
      
      if(Protocols.AJP.equals(protocol))
      {
         result = new JettyServer(protocol, name, target, address, port);
      }
      else if(Protocols.HTTP.equals(protocol)) 
      {
         result = new JettyServer(protocol, name, target, address, port);
      }
      else if(Protocols.HTTPS.equals(protocol)) 
      {
         result = new JettyServer(protocol, name, target, address, port);
      }
      
      return result;
   }
   
   /**
    * Creates a new uniform call.
    * @return A new uniform call.
    */
   public UniformCall createCall()
   {
      return new UniformCallImpl();
   }

   /**
    * Creates a delegate Restlet server.
    * @param parent The parent Restlet server.
    * @param name The server's name.
    * @return The new Restlet server.
    */
   public RestletServer createRestletServer(RestletServer parent, String name)
   {
      return new RestletServerImpl(name);
   }

   /**
    * Creates a delegate Restlet container.
    * @param parent The parent Restlet container.
    * @param name The container's name.
    * @return The new Restlet container.
    */
   public RestletContainer createRestletContainer(RestletContainer parent, String name)
   {
      return new RestletContainerImpl(parent, name);
   }

   /**
    * Creates a delegate Maplet.
    * @param container The Restlet container.
    * @return A new Maplet.
    */
   public Maplet createMaplet(RestletContainer container)
   {
      return new MapletImpl(container);
   }

   /**
    * Creates a delegate Chainlet.
    * @param container The Restlet container.
    * @return A new Chainlet.
    */
   public Chainlet createChainlet(RestletContainer container)
   {
      return new ChainletImpl(container);
   }

   /**
    * Returns a new cookie.
    * @param name The name.
    * @param value The value.
    * @return A new cookie.
    */
   public Cookie createCookie(String name, String value)
   {
      return new CookieImpl(name, value);
   }

   /**
    * Returns a new cookie setting.
    * @param name The name.
    * @param value The value.
    * @return A new cookie setting.
    */
   public CookieSetting createCookieSetting(String name, String value)
   {
      return new CookieSettingImpl(name, value);
   }

   /**
    * Creates an empty form.
    * @return A new form.
    */
   public Form createForm()
   {
      return new FormImpl();
   }

   /**
    * Creates a new reference from a URI reference.
    * @param uriReference The URI reference.
    * @return The new URI reference.
    */
   public Reference createReference(String uriReference)
   {
      return new ReferenceImpl(uriReference);
   }

   /**
    * Creates a new character set from its standard name.
    * @param name The standard character set name.
    * @return The new character set.
    */
   public CharacterSet createCharacterSet(String name)
   {
      return new CharacterSetImpl(name);
   }

   /**
    * Creates a new encoding from its standard name.
    * @param name The standard encoding name.
    * @return The new encoding.
    */
   public Encoding createEncoding(String name)
   {
      return new EncodingImpl(name);
   }

   /**
    * Creates a new language from its standard name.
    * @param name The standard language name.
    * @return The new language.
    */
   public Language createLanguage(String name)
   {
      return new LanguageImpl(name);
   }

   /**
    * Creates a new media type from its standard name.
    * @param name The standard media type name.
    * @return The new media type.
    */
   public MediaType createMediaType(String name)
   {
      return new MediaTypeImpl(name);
   }

   /**
    * Creates a new method from its standard name.
    * @param name The standard method name.
    * @return The new method.
    */
   public Method createMethod(String name)
   {
      return new MethodImpl(name);
   }

   /**
    * Creates a new status from its standard code.
    * @param code The standard status code.
    * @return The new status.
    */
   public Status createStatus(int code)
   {
      return new StatusImpl(code);
   }

   /**
    * Creates a new tag.
    * @param name The tag name.
    * @return The new tag.
    */
   public Tag createTag(String name)
   {
      return new TagImpl(name);
   }

   /**
    * Creates a new parameter.
    * @param name The parameter's name.
    * @param value The parameter's value.
    * @return The new parameter.
    */
   public Parameter createParameter(String name, String value)
   {
      return new ParameterImpl(name, value);
   }

}
