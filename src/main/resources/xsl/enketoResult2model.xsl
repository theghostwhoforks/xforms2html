<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="2.0"
        xmlns:json="http://json.org/"
        xmlns:forms="http://www.w3.org/2002/xforms"
        >
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes" version="1.0" encoding="UTF-8"/>

    <xsl:variable name="formName">
        <xsl:value-of select="name(model/*:instance/node()[2])"/>
    </xsl:variable>

    <xsl:template match="/">
        <form>
            <xsl:apply-templates select="model/*:instance/node()"/>
            <xsl:apply-templates select="model/*:instance/node()//node()[not(@template) and not(ancestor::*[@template])]"/>
            <xsl:apply-templates select="model/*:instance/node()//node()[@template]"/>
        </form>
    </xsl:template>

    <xsl:template match="/model/*:instance/node()">
        <xsl:call-template name="bind">
            <xsl:with-param name="bind_value" select="current()"/>
        </xsl:call-template>
    </xsl:template>


    <xsl:template match="model/*:instance/node()//node()[not(@template) and not(ancestor::*[@template])]">
        <xsl:call-template name="copy-model">
            <xsl:with-param name="model" select="current()"/>
        </xsl:call-template>
    </xsl:template>

    <xsl:template match="model/*:instance/node()//node()[@template]">
        <xsl:call-template name="copy-repeat-model">
            <xsl:with-param name="model" select="current()"/>
        </xsl:call-template>
    </xsl:template>

    <xsl:template name="bind">
        <xsl:param name="bind_value"/>
        <xsl:if test="name($bind_value[1])">
            <xsl:element name="bind_type">
                <xsl:value-of select="local-name($bind_value)"/>
            </xsl:element>
            <xsl:element name="default_bind_path">
                <xsl:call-template name="genPath"/>
            </xsl:element>
        </xsl:if>
    </xsl:template>

    <xsl:template name="copy-model">
        <xsl:param name="model"/>
        <xsl:if test="name($model[1])">
            <xsl:if test="not($model[@template])">
                <xsl:element name="fields">
                    <xsl:attribute name="json:force-array" select="true()"/>

                    <xsl:attribute name="name">
                        <xsl:value-of select="local-name($model)"/>
                    </xsl:attribute>
                    <xsl:attribute name="bind">
                        <xsl:call-template name="genPath"/>
                    </xsl:attribute>
                </xsl:element>
            </xsl:if>
        </xsl:if>
    </xsl:template>

    <xsl:template name="copy-repeat-model">
        <xsl:param name="model"/>
        <xsl:if test="name($model[1])">
            <xsl:if test="$model[@template]">
                <xsl:element name="sub_forms">
                    <xsl:attribute name="json:force-array" select="true()"/>
                    <xsl:attribute name="name">
                        <xsl:value-of select="local-name($model)"/>
                    </xsl:attribute>
                    <xsl:attribute name="bind_type">
                        <xsl:copy-of select="$formName"/>
                    </xsl:attribute>
                    <xsl:attribute name="default_bind_path">
                        <xsl:call-template name="genPath"/>
                    </xsl:attribute>
                    <xsl:if test="$model/*">
                        <xsl:for-each select="$model/node()">
                            <xsl:call-template name="copy-model">
                                <xsl:with-param name="model" select="."/>
                            </xsl:call-template>
                        </xsl:for-each>
                    </xsl:if>
                </xsl:element>
            </xsl:if>
        </xsl:if>
    </xsl:template>

    <xsl:template name="genPath">
        <xsl:param name="prevPath"/>
        <xsl:variable name="currPath" select="concat('/',name(),
        ''
      ,$prevPath)"/>
        <xsl:for-each select="parent::*">
            <xsl:call-template name="genPath">
                <xsl:with-param name="prevPath" select="$currPath"/>
            </xsl:call-template>
        </xsl:for-each>
        <xsl:if test="not(parent::*)">
            <xsl:value-of select="$currPath"/>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>