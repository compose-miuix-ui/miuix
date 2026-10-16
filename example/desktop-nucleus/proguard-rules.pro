# Workaround for ProGuard VerifyError on SkiaParagraph:
# ProGuard's method-specialization optimization pass rewrites Paragraph-czeN-Hc
# with concrete return type SkiaParagraph while the method body returns Paragraph interface,
# causing JVM java.lang.VerifyError: Bad return type.
-optimizations !method/specialization/*
