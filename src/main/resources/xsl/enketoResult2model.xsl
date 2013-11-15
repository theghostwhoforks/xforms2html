<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="2.0"
        xmlns:json="http://json.org/"
        xmlns:forms="http://www.w3.org/2002/xforms"
        >
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes" version="1.0" encoding="UTF-8"/>
    <xsl:template match="/">
        <form>
                <xsl:apply-templates select="/model/*:instance/node()"/>
                <xsl:apply-templates select="/model/*:instance/node()/node()"/>
        </form>
    </xsl:template>



    <!--<xsl:template match="/root/*[local-name() != 'model']"/>-->
    <!--<xsl:template match="/root/model/instance/*[local-name() = 'root']"/>-->

    <xsl:template match="/model/*:instance/node()">
        <xsl:call-template name="bind">
            <xsl:with-param name="bind_value" select="current()"/>
        </xsl:call-template>
    </xsl:template>

    <xsl:template match="/model/*:instance/node()/node()">
        <xsl:call-template name="copy-model">
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
                <xsl:value-of select="local-name($bind_value)"/>
            </xsl:element>
        </xsl:if>
    </xsl:template>

    <xsl:template name="copy-model">
        <xsl:param name="model"/>
        <xsl:if test="name($model[1])">
            <xsl:element name="fields">
                <xsl:attribute name="json:force-array" select="true()"/>

                <xsl:attribute name="name">
                    <xsl:value-of select="local-name($model)"/>
                </xsl:attribute>
                <xsl:attribute name="bind">
                    <xsl:call-template name="genPath"/>
                </xsl:attribute>
            </xsl:element>

            <xsl:choose>
                <xsl:when test="$model/*">
                    <xsl:for-each select="$model/node()">
                        <xsl:call-template name="copy-model">
                            <xsl:with-param name="model" select="."/>
                        </xsl:call-template>
                    </xsl:for-each>
                </xsl:when>
            </xsl:choose>
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