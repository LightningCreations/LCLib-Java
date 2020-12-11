package github.lightningcreations.lclib.constant;

import github.lightningcreations.lclib.Version;
import github.lightningcreations.lclib.annotation.ValueBased;

import java.lang.constant.*;

@ValueBased
public final class VersionDesc extends DynamicConstantDesc<Version> {

    public static final VersionDesc v1_0 = fromFields("v1_0",1,0);

    public static VersionDesc fromVersion(String constantName,String value){
        return new VersionDesc(MethodHandleDesc.of(DirectMethodHandleDesc.Kind.STATIC,ClassDesc.ofDescriptor("github/lightningcreations/lclib/Version"),"valueOf","(Ljava/lang/String;)Lgithub/lightningcreations/lclib/Version;"),
            constantName,ClassDesc.ofDescriptor("github/lightningcreations/lclib/Version"),value);
    }

    public static VersionDesc fromFields(String constantName,int major,int minor){
        return new VersionDesc(MethodHandleDesc.of(DirectMethodHandleDesc.Kind.STATIC,ClassDesc.ofDescriptor("github/lightningcreations/lclib/Version"),"valueOf","(II)Lgithub/lightningcreations/lclib/Version;"),
            constantName,ClassDesc.ofDescriptor("github/lightningcreations/lclib/Version"),major,minor);
    }



    protected VersionDesc(DirectMethodHandleDesc bootstrapMethod, String constantName, ClassDesc constantType, ConstantDesc... bootstrapArgs) {
        super(bootstrapMethod,constantName, constantType, bootstrapArgs);
    }
}
