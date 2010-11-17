package com.mattinsler.cement.example.handler;

import com.google.inject.Inject;
import com.google.inject.Key;
import com.mattinsler.cement.annotation.Get;
import com.mattinsler.cement.example.annotation.Authenticated;
import com.mattinsler.cement.example.model.UserEntity;
import com.mattinsler.cement.routing.CementMethod;
import com.mattinsler.cement.routing.CementMethodRouter;
import com.mattinsler.cement.routing.CementParameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 3, 2010
 * Time: 10:55:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntrospectionHandler {
//    static class HtmlWriter implements CementResponseWriter {
//        private String formatPath(List<CementMethod.PathToken> tokens) {
//            return StringUtil.join(tokens, "/", new Function<String, CementMethod.PathToken>() {
//                public String execute(CementMethod.PathToken argument) {
//                    if (argument.isParameter) {
//                        return "<input type=\"text\" name=\"" + argument.name + "\" value=\"" + argument.name + "\" />";
//                    } else {
//                        return argument.name;
//                    }
//                }
//            });
//        }
//
//        private String formatParameters(Collection<CementParameter<?>> parameters) {
//            return (parameters.size() > 0 ? "?" : "") + StringUtil.join(parameters, "&", new Function<String, CementParameter<?>>() {
//                public String execute(CementParameter<?> argument) {
//                    return argument.name + "=<input type=\"text\" name=\"" + argument.name + "\" value=\"" + argument.name + "\" />";
//                }
//            });
//        }
//
//        public String write(Object value, PrintWriter writer) throws IOException {
//            Set<CementMethodRouter> routers = (Set<CementMethodRouter>)value;
//            for (CementMethodRouter router : routers) {
//                String urlPattern = router.urlPattern;
//                if (urlPattern.endsWith("*")) {
//                    urlPattern = urlPattern.substring(0, urlPattern.length() - 1);
//                }
//                for (CementMethod method : router.methods) {
//                    writer.printf("[%s] %s%s%s<br />", method.type, urlPattern, formatPath(method.pathTokens), formatParameters(method.parameters.values()));
//                }
//            }
//            return "text/html";
//        }
//
//        public String getFormat() {
//            return "html";
//        }
//    }

    public static class PathItem {
        public String value;
        public boolean isParameter;
    }

    public static class Method {
        public String type;
        public String url;
        public boolean isAuthenticated;
        public List<String> parameters;
        public List<PathItem> path;
    }

    private final Set<CementMethodRouter> _routers;

    @Inject
    IntrospectionHandler(Set<CementMethodRouter> routers) {
        _routers = routers;
    }

    private static boolean isAuthenticated(CementMethod method) {
        for (CementParameter<?> p : method.injectableParameters) {
            if (Key.get(UserEntity.class, Authenticated.class).equals(p.bindingKey)) {
                return true;
            }
        }
        return false;
    }

//    @Get(defaultFormat = "html")
//    @ResponseWriters(HtmlWriter.class)
    @Get
    public List<Method> get() {
        List<Method> methods = new ArrayList<Method>();

        for (CementMethodRouter router : _routers) {
            for (CementMethod cementMethod : router.methods) {
                Method method = new Method();
                method.type = cementMethod.type.name();
                method.url = router.urlPattern;
                method.isAuthenticated = isAuthenticated(cementMethod);
                if (method.url.endsWith("/*")) {
                    method.url = method.url.substring(0, method.url.length() - 2);
                }
                if (cementMethod.parameters.size() > 0) {
                    method.parameters = new ArrayList<String>(cementMethod.parameters.keySet());
                }
                if (cementMethod.pathTokens.size() > 0) {
                    method.path = new ArrayList<PathItem>();
                    for (CementMethod.PathToken token : cementMethod.pathTokens) {
                        PathItem item = new PathItem();
                        item.value = token.name;
                        item.isParameter = token.isParameter;
                        method.path.add(item);
                    }
                }
                methods.add(method);
            }
        }

        return methods;
    }
}
