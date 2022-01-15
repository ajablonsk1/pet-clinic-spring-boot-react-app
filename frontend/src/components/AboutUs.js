import React from 'react';
import {Card} from 'react-bootstrap';

const AboutUs = () => {
    return (
        <div>
            <Card className="w-50" style={{marginLeft: 'auto', marginRight: 'auto', marginTop: '5rem'}}>
                <Card.Header as="h5">About us</Card.Header>
                <Card.Body>
                    <Card.Text>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin suscipit venenatis eros, et volutpat sem. Pellentesque tincidunt est in orci maximus posuere. In facilisis rutrum sapien ut finibus. Nullam suscipit, enim non feugiat rhoncus, enim purus scelerisque augue, tempus interdum arcu massa id orci. Quisque euismod turpis nibh, eu interdum elit tincidunt a. Etiam consequat vel augue vel imperdiet. Phasellus vitae mauris ornare, consequat magna et, fringilla mauris. In mi nunc, volutpat a tincidunt eget, venenatis et lectus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.
                    </Card.Text>
                </Card.Body>
            </Card>
        </div>
    )
}

export default AboutUs
