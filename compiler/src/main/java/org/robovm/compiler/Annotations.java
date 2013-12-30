/*
 * Copyright (C) 2012 Trillian AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import soot.SootClass;
import soot.SootMethod;
import soot.tagkit.AnnotationAnnotationElem;
import soot.tagkit.AnnotationArrayElem;
import soot.tagkit.AnnotationElem;
import soot.tagkit.AnnotationTag;
import soot.tagkit.Host;
import soot.tagkit.VisibilityAnnotationTag;
import soot.tagkit.VisibilityParameterAnnotationTag;

/**
 * @author niklas
 *
 */
public class Annotations {
    public static final String BRIDGE = "Lorg/robovm/rt/bro/annotation/Bridge;";
    public static final String CALLBACK = "Lorg/robovm/rt/bro/annotation/Callback;";
    public static final String STRUCT_MEMBER = "Lorg/robovm/rt/bro/annotation/StructMember;";
    public static final String ARRAY = "Lorg/robovm/rt/bro/annotation/Array;";
    public static final String BASE_TYPE = "Lorg/robovm/rt/bro/annotation/BaseType;";
    public static final String STRUCT_RET = "Lorg/robovm/rt/bro/annotation/StructRet;";
    public static final String POINTER = "Lorg/robovm/rt/bro/annotation/Pointer;";
    public static final String MARSHALER = "Lorg/robovm/rt/bro/annotation/Marshaler;";
    public static final String MARSHALERS = "Lorg/robovm/rt/bro/annotation/Marshalers;";
    public static final String MARSHALS_POINTER = "Lorg/robovm/rt/bro/annotation/MarshalsPointer;";
    public static final String MARSHALS_VALUE = "Lorg/robovm/rt/bro/annotation/MarshalsValue;";
    public static final String MARSHALS_ARRAY = "Lorg/robovm/rt/bro/annotation/MarshalsArray;";
    public static final String AFTER_BRIDGE_CALL = "Lorg/robovm/rt/bro/annotation/AfterBridgeCall;";
    public static final String AFTER_CALLBACK_CALL = "Lorg/robovm/rt/bro/annotation/AfterCallbackCall;";
    public static final String BY_VAL = "Lorg/robovm/rt/bro/annotation/ByVal;";
    public static final String BY_REF = "Lorg/robovm/rt/bro/annotation/ByRef;";

    public static boolean hasAnnotation(Host host, String annotationType) {
        return getAnnotation(host, annotationType) != null;
    }
    
    public static boolean hasParameterAnnotation(SootMethod method, int paramIndex, String annotationType) {
        return getParameterAnnotation(method, paramIndex, annotationType) != null;
    }

    public static List<AnnotationTag> getAnnotations(Host host) {
        VisibilityAnnotationTag vatag = (VisibilityAnnotationTag) host.getTag("VisibilityAnnotationTag");
        if (vatag != null) {
            return vatag.getAnnotations();
        }
        return Collections.emptyList();
    }
    
    public static AnnotationTag getAnnotation(Host host, String annotationType) {
        for (AnnotationTag tag : getAnnotations(host)) {
            if (annotationType.equals(tag.getType())) {
                return tag;
            }                    
        }
        return null;
    }
    
    public static List<AnnotationTag> getParameterAnnotations(SootMethod method, int paramIndex) {
        VisibilityParameterAnnotationTag vpatag = (VisibilityParameterAnnotationTag) method.getTag("VisibilityParameterAnnotationTag");
        if (vpatag != null) {
            List<VisibilityAnnotationTag> tags = vpatag.getVisibilityAnnotations();
            List<AnnotationTag> annotations = tags.get(paramIndex).getAnnotations();
            if (annotations != null) {
                return annotations;
            }
        }
        return Collections.emptyList();
    }

    public static AnnotationTag getParameterAnnotation(SootMethod method, int paramIndex, String annotationType) {
        for (AnnotationTag tag : getParameterAnnotations(method, paramIndex)) {
            if (annotationType.equals(tag.getType())) {
                return tag;
            }                    
        }
        return null;
    }
    
    public static boolean hasBridgeAnnotation(SootMethod method) {
        return hasAnnotation(method, BRIDGE);
    }
    
    public static boolean hasCallbackAnnotation(SootMethod method) {
        return hasAnnotation(method, CALLBACK);
    }
    
    public static boolean hasStructMemberAnnotation(SootMethod method) {
        return hasAnnotation(method, STRUCT_MEMBER);
    }
    
    public static boolean hasArrayAnnotation(SootMethod method) {
        return hasAnnotation(method, ARRAY);
    }

    public static boolean hasArrayAnnotation(SootMethod method, int paramIndex) {
        return hasParameterAnnotation(method, paramIndex, ARRAY);
    }
    
    public static boolean hasPointerAnnotation(SootMethod method) {
        return hasAnnotation(method, POINTER);
    }

    public static boolean hasByValAnnotation(SootMethod method) {
        return hasAnnotation(method, BY_VAL);
    }
    
    public static boolean hasByRefAnnotation(SootMethod method) {
        return hasAnnotation(method, BY_REF);
    }
    
    public static boolean hasStructRetAnnotation(SootMethod method, int paramIndex) {
        return hasParameterAnnotation(method, paramIndex, STRUCT_RET);
    }
    
    public static boolean hasPointerAnnotation(SootMethod method, int paramIndex) {
        return hasParameterAnnotation(method, paramIndex, POINTER);
    }
    
    public static boolean hasByValAnnotation(SootMethod method, int paramIndex) {
        return hasParameterAnnotation(method, paramIndex, BY_VAL);
    }
    
    public static boolean hasByRefAnnotation(SootMethod method, int paramIndex) {
        return hasParameterAnnotation(method, paramIndex, BY_REF);
    }
    
    public static boolean hasAfterBridgeCallAnnotation(SootMethod method) {
        return hasAnnotation(method, AFTER_BRIDGE_CALL);
    }
    
    public static boolean hasAfterCallbackCallAnnotation(SootMethod method) {
        return hasAnnotation(method, AFTER_CALLBACK_CALL);
    }

    public static AnnotationTag getMarshalerAnnotation(SootMethod method, int paramIndex) {
        return getParameterAnnotation(method, paramIndex, MARSHALER);
    }
    
    public static AnnotationTag getMarshalerAnnotation(SootMethod method) {
        return getAnnotation(method, MARSHALER);
    }
    
    public static List<AnnotationTag> getMarshalerAnnotations(SootClass clazz) {
        List<AnnotationTag> tags = new ArrayList<AnnotationTag>();
        AnnotationTag marshaler = getAnnotation(clazz, MARSHALER);
        if (marshaler != null) {
            tags.add(marshaler);
        } else {
            AnnotationTag marshalers = getAnnotation(clazz, MARSHALERS);
            if (marshalers != null) {
                for (AnnotationElem e : ((AnnotationArrayElem) marshalers.getElemAt(0)).getValues()) {
                    AnnotationAnnotationElem elem = (AnnotationAnnotationElem) e;
                    tags.add(elem.getValue());
                }
            }
        }
        
        return tags;
    }
    
    public static AnnotationTag getMarshalsPointerAnnotation(SootMethod method) {
        return getAnnotation(method, MARSHALS_POINTER);
    }

    public static AnnotationTag getMarshalsValueAnnotation(SootMethod method) {
        return getAnnotation(method, MARSHALS_VALUE);
    }
    
    public static AnnotationTag getMarshalsArrayAnnotation(SootMethod method) {
        return getAnnotation(method, MARSHALS_ARRAY);
    }

    public static AnnotationTag getStructMemberAnnotation(SootMethod method) {
        return getAnnotation(method, STRUCT_MEMBER);
    }
    
    public static AnnotationTag getArrayAnnotation(SootMethod method) {
        return getAnnotation(method, ARRAY);
    }

    public static AnnotationTag getArrayAnnotation(SootMethod method, int paramIndex) {
        return getParameterAnnotation(method, paramIndex, ARRAY);
    }
    
    public static AnnotationTag getBaseTypeAnnotation(SootMethod method) {
        return getAnnotation(method, BASE_TYPE);
    }

    public static boolean hasByValAnnotation(SootClass clazz) {
        while (!clazz.isPhantom() && clazz.hasSuperclass()) {
            if (hasAnnotation(clazz, BY_VAL)) {
                return true;
            }
            if (hasAnnotation(clazz, BY_REF)) {
                return false;
            }
            clazz = clazz.getSuperclass();
        }
        return false;
    }
    
    public static boolean hasByRefAnnotation(SootClass clazz) {
        while (!clazz.isPhantom() && clazz.hasSuperclass()) {
            if (hasAnnotation(clazz, BY_REF)) {
                return true;
            }
            if (hasAnnotation(clazz, BY_VAL)) {
                return false;
            }
            clazz = clazz.getSuperclass();
        }
        return false;
    }
    
    public static AnnotationElem getElemByName(AnnotationTag annotation, String name) {
        for (int i = 0; i < annotation.getNumElems(); i++) {
            AnnotationElem elem = annotation.getElemAt(i);
            if (name.equals(elem.getName())) {
                return elem;
            }
        }
        return null;
    }
}
