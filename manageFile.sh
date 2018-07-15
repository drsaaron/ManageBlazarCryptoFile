#! /bin/ksh

while getopts :u:r:p: option
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
	*)
	    echo "invalid option $OPTION"
	    echo "usage: $(basename $0) [-u user -r resource -p password]" 1>&2
	    exit 1
    esac
done

# build command line
if [[ $# > 0 ]]
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
#exec mvn exec:java -Dexec.args="$commandLine"
java -jar target/ManageBlazarCryptoFile-1.0-SNAPSHOT.jar $commandLine

# ensure the key file is properly protected
chmod 600 ~/.blazartech/crypto/crypto.key
