#! /bin/sh

while getopts :u:r:p:l option
do
    case $option in
	u)
	    user=$OPTARG
	    ;;
	r)
	    resource=$OPTARG
	    ;;
	p)
	    password=$OPTARG
	    ;;
	l)
	    doList=true
	    ;;
	*)
	    echo "invalid option $OPTION"
	    echo "usage: $(basename $0) [-u user -r resource -p password]" 1>&2
	    exit 1
    esac
done

# build command line
if [ "$doList" = "true" ]
then
    commandLine="list"
elif [ $# -gt 0 ]
then
    commandLine="$user $resource $password"
else
    commandLine=
fi

# initial directory setup, if not already there.
[ -d ~/.blazartech/crypto ] || mkdir -p ~/.blazartech/crypto
if [ ! -f ~/.blazartech/crypto.properties ]
then
    cat > ~/.blazartech/crypto.properties <<EOF
blazartech.crypto.file=\${user.home}/.blazartech/crypto/crypto.dat
blazartech.crypto.keyFile=\${user.home}/.blazartech/crypto/crypto.key
EOF

fi

# run
java -jar target/ManageBlazarCryptoFile-$(getPomAttribute.sh version).jar $commandLine

# ensure the key file is properly protected
chmod 600 ~/.blazartech/crypto/crypto.key
