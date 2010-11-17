package com.mattinsler.cement.example.annotation.processor;

import com.mattinsler.cement.example.annotation.PublicApi;
import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.util.Context;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 23, 2010
 * Time: 3:33:39 AM
 * To change this template use File | Settings | File Templates.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("com.mattinsler.cement.example.annotation.PublicApi")
//@SupportedAnnotationTypes("*")
public class PublicApiProcessor extends AbstractProcessor {
//    private ProcessingEnvironment _environment;
//
//    public Set<String> getSupportedOptions() {
//        return Collections.emptySet();
//    }
//
//    public Set<String> getSupportedAnnotationTypes() {
//        return Collections.singleton(PublicApi.class.getSimpleName());
//    }
//
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.RELEASE_6;
//    }
//
//    public void init(ProcessingEnvironment environment) {
//        _environment = environment;
//    }
//
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
//        for (TypeElement e : annotations) {
//            System.out.println(e.getQualifiedName());
//        }
//        return true;
//    }
//
//    public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member, String userText) {
//        return null;
//    }

    protected Trees _trees;
    protected ProcessingEnvironment _processingEnv;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

//        if (!(processingEnv instanceof JavacProcessingEnvironment)) {
//            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Immuter requires javac v1.6.");
//            return;
//        }

        Context ctx = ((JavacProcessingEnvironment)processingEnv).getContext();
        _trees = Trees.instance(processingEnv);
        _processingEnv = processingEnv;

        // note our options
//        _handleStar = "true".equalsIgnoreCase(processingEnv.getOptions().get(HANDLE_STAR));

        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Immuter running [vers=" + processingEnv.getSourceVersion() + "]");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Entered AnnotationEntityProcessor.process ...");

        for (Element e : roundEnv.getElementsAnnotatedWith(PublicApi.class)) {
            // handle(e)
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, e.getSimpleName());
            ExecutableElement method = (ExecutableElement)e;
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Returns " + method.getReturnType());
        }
        if (roundEnv.processingOver()) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "EntityProcessor processing completed.");
            // write everything
        }
        return false;

//        if (_trees == null) {
//            return false;
//        }
//
//        for (Element elem : roundEnv.getRootElements()) {
//            final JCTree.JCCompilationUnit unit = toUnit(elem);
//
//            // we only want to operate on files being compiled from source; if they're already
//            // classfiles then we've already run or we're looking at a library class
//            if (unit.sourcefile.getKind() != JavaFileObject.Kind.SOURCE) {
//                System.err.println("Skipping non-source-file " + unit.sourcefile);
//                continue;
//            }
//
//            // System.err.println("Processing " + unit.sourcefile);
//            unit.accept(new TreeTranslator() {
//                public void visitVarDef(JCTree.JCVariableDecl tree) {
//                    super.visitVarDef(tree);
//
//                    // if this variable declaration's modifiers have already been processed
//                    // (variables can share modifiers, ie. public @var int foo, bar), then don't
//                    // repeat process this declaration
//                    if (_seen.contains(tree.mods)) {
//                        return;
//                    }
//                    _seen.add(tree.mods);
//
//                    // note the number of annotations on this var
//                    int ocount = tree.mods.annotations.size();
//
//                    // remove the @var annotation if we see it
//                    tree.mods.annotations = removeVar(tree.mods.annotations);
//
//                    // if we didn't remove anything, then make the variable final
//                    if (tree.mods.annotations.size() == ocount) {
//                        tree.mods.flags |= Flags.FINAL;
//
//                        // check for retardation
//                    } else if ((tree.mods.flags & Flags.FINAL) != 0) {
//                        _processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "@var annotated variable also marked final: " + tree,
//                                // TODO: this should work but it doesn't, sigh
//                                _trees.getElement(TreePath.getPath(unit, tree)));
//                    }
//                }
//
//                protected Set<JCTree.JCModifiers> _seen = new HashSet<JCTree.JCModifiers>();
//            });
//        }
//
//        // TODO: it would be nice if we could say that we handled @var but there seems to be no way
//        // to say you accept "*" but then tell javac you handled some of the annotations you saw
//        return _handleStar;
    }
}
